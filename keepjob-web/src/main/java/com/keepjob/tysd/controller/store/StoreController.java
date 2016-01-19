package com.keepjob.tysd.controller.store;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.pagination.PaginationResultSet;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.sys.user.User;
import com.keepjob.tysd.store.Store;
import com.keepjob.tysd.store.StoreHandler;
import com.keepjob.tysd.store.vo.StoreVO;

@Controller
@RequestMapping("/tysd/store")
public class StoreController {

	@Autowired
	private StoreHandler storeHandler;
	@Autowired
	private Gson gson;
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	private String toIndex(HttpServletRequest request, HttpSession session){
	    request.setAttribute("types", storeHandler.findStoreTypes());
		return "tysd/store/storeView";
	}
	
	@RequestMapping(value = "/searchStore.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchStoreJson(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "name",required = false) String name,
			@RequestParam(value = "uniqueCode", required = false) String uniqueCode,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(required=false, value="sort") String sort,
			@RequestParam(required=false, value="order") String order, 
			@RequestParam(required=false, value="page") Integer page,
			@RequestParam(required=false, value="rows") Integer rows, Model model){
		StoreVO vo = new StoreVO();
		vo.setUniqueCode(uniqueCode);
		vo.setName(name);
		vo.setStatus(status);
		vo.setType(type);
		vo.setSort(sort);
		vo.setOrder(order);
		vo.setPage(page);
		vo.setPage(page);
		PaginationResultSet<Store> result = this.storeHandler.findStoreByPagination(vo);
		return gson.toJson(result);
	}
	
	@RequestMapping(value = "/addStore.html", method = RequestMethod.GET)
	public String toAddStoreView(HttpSession session, HttpServletRequest request){
		request.setAttribute("types", storeHandler.findStoreTypes());
		return "tysd/store/storeAdd";
	}
	
	@RequestMapping(value = "/addStoreContent.html", method = RequestMethod.GET)
	public String toAddStoreContentView(HttpSession session, HttpServletRequest request, @ModelAttribute("parameter") Store parameter){
		request.setAttribute("result", parameter);
		return "tysd/store/storeContentAdd";
	}
	
	@RequestMapping(value = "/editStore.html", method = RequestMethod.GET)
	public String toEditStoreView(HttpSession session, HttpServletRequest request, @ModelAttribute("parameter") Store parameter){
		request.setAttribute("result", parameter);
		request.setAttribute("types", storeHandler.findStoreTypes());
		return "tysd/store/storeEdit";
	}
	
	@RequestMapping(value = "/saveStore.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String saveStore(HttpSession session, HttpServletRequest request, @ModelAttribute("parameter") Store parameter){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			System.out.println("保存店铺信息");
			this.storeHandler.saveOrUpdateStore(user, parameter);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/startStore.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String startStores(HttpSession session, HttpServletRequest request, @RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.storeHandler.startStores(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/stopStore.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String stopStores(HttpSession session, HttpServletRequest request, @RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.storeHandler.stopStores(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/removeStore.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String removeStores(HttpSession session, HttpServletRequest request, @RequestParam(value = "id", required = false) List<Integer> ids){
		try {
			User user = (User) session.getAttribute(Constant.USER_KEY);
			this.storeHandler.removeStores(user, ids);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value="/fileUpload.json")
	@ResponseBody
	public String uploadImages(@RequestParam MultipartFile[] logo,
			HttpServletRequest request, 
			HttpServletResponse response)
			throws IOException{
		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
		String realPath = request.getSession().getServletContext().getRealPath("/images");
		//设置响应给前台内容的数据格式
		response.setContentType("text/plain; charset=UTF-8");
		//response.setContentType("application/json; charset=UTF-8");
		//设置响应给前台内容的PrintWriter对象
		//PrintWriter out = response.getWriter();
		//上传文件的原名(即上传前的文件名字)
		String originalFilename = null;
		//如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
        //如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
        //上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
		Map<String,Object> result = new HashMap<String, Object>();
		//LoadUpObject lo = new LoadUpObject();
		for(MultipartFile myfile : logo){
			if(myfile.isEmpty()){
				result.put("result", "1");
				result.put("message", "请选择文件后上传");
				//lo.setResult("1");
				//lo.setMessage("请选择文件后上传");
				return gson.toJson(result);
			}else{
				originalFilename = myfile.getOriginalFilename();
				 System.out.println("文件原名: " + originalFilename);
	             System.out.println("文件名称: " + myfile.getName());
	             System.out.println("文件长度: " + myfile.getSize());
	             System.out.println("文件类型: " + myfile.getContentType());
	             System.out.println("========================================");
	             try {
	            	FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
				} catch (IOException e) {
					// TODO: handle exception
					 System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
	                 e.printStackTrace();
	                result.put("result", "1");
	 				result.put("message", "文件上传失败，请重试！！");
	 				return gson.toJson(result);
				}
			}
		}
		 result.put("result", "0");
		 result.put("message", request.getContextPath() + "/images/" + originalFilename);
		 return gson.toJson(result);
	}
	
	@ModelAttribute("parameter")
	private Store getParameter(@RequestParam(value = "id", required = false) Integer id, Model model){
		Store parameter = null;
		if(null == id){
			parameter = new Store();
		}else{
			parameter = this.storeHandler.getStore(id);
		}
		return parameter;
	}
	
	 
}
