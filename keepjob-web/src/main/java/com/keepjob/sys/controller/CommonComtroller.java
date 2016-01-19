package com.keepjob.sys.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.code.kaptcha.Producer;
import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.util.FileUtils;


@Controller
@RequestMapping("/common")
public class CommonComtroller {
	
	@Autowired
	private Gson gson;
	
	@RequestMapping(value="/fileUpload.json")
	@ResponseBody
	public String uploadImages(@RequestParam MultipartFile[] logoFile,
			HttpServletRequest request, 
			HttpServletResponse response)
			throws IOException{
		//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
		String realPath = request.getSession().getServletContext().getRealPath("/upload");
		//设置响应给前台内容的数据格式
		response.setContentType("text/html; charset=UTF-8");
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
		for(MultipartFile myfile : logoFile){
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
		 result.put("message", request.getContextPath() + "/upload/" + originalFilename);
		 System.out.println(gson.toJson(result));
		 return gson.toJson(result);
	}
	
	/*@Autowired
	private CountyHandler countyHandler;
	@Autowired
	private OrganHandler organHandler;
	@Autowired
	private OfficeHandler officeHandler;
	@Autowired
	private DiagnosisOrganHandler diagnosisOrganHandler;*/
	//@Autowired
	/*private Gson gson;
	*//**
	 * 根据查询码，查询对应信息
	 * @param request
	 * @param searchCode 
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = "/searchCountys.json", method = {RequestMethod.GET, RequestMethod.POST}, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchCountys(HttpServletRequest request, 
			@RequestParam(required=false, value="searchCode") String searchCode, Model model){
		List<County> countys = this.countyHandler.findCountys(searchCode);
		return gson.toJson(countys);
	}
	
	*//**
	 * 获取所属单位
	 * @param request
	 * @param userType
	 * @param searchCode
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = "/getorgans.json", method ={RequestMethod.GET, RequestMethod.POST}, produces=Constant.APPLICATION_JSON)
	@ResponseBody
	public String getUserUnits(HttpSession session, HttpServletRequest request, @RequestParam(required=false, value="searchCode") String searchCode, Model model){
		List<Organ> organs = new ArrayList<Organ>();
		try{
			User user = (User) session.getAttribute(Constant.USER_KEY);
			if(user.isHospitalUser()){
				Organ organ = this.organHandler.getOrganByUniqueCode(user.getUserUnit());
				organs.add(organ);
			}else{
				organs = this.organHandler.findEnableOrgans(searchCode);
			}
			return gson.toJson(organs);
		}catch(Exception ex){
			ex.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(ex.getMessage()));
		}
	}
	
	*//**
	 * 根据查项码，查询对于的科室
	 * @param request
	 * @param searchCode
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = "/searchOffices.json", method = RequestMethod.GET, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String getOffice(HttpServletRequest request, @RequestParam(required = false, value = "searchCode") String searchCode,
			Model model){
			List<Office> offices = this.officeHandler.findOffices(searchCode);
			return gson.toJson(offices);
	}
	*//**
	 * 根据编码查询科室
	 * @param request
	 * @param code
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = "/getOffice.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String getOfficeCode(HttpServletRequest request,
			@RequestParam(required = false, value="code")String code,
			Model model){
		Office office = this.officeHandler.getOffice(code);
		return gson.toJson(office);
	}
	
	*//**
	 * 根据查询码搜索数据
	 * @param request
	 * @param searchCode
	 * @param session
	 * @return
	 *//*
	@RequestMapping(value = "/searchDiagnosisOrgans.json", method = {RequestMethod.GET, RequestMethod.POST}, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String searchDiagnosisOrgans(HttpServletRequest request,
			@RequestParam(value="searchCode", required = false ) String searchCode,
			HttpSession session){
		List<DiagnosisOrgan> result= this.diagnosisOrganHandler.findDiagnosisOrgansBySearchCode(searchCode);
		return gson.toJson(result);
	}*/
	
	
}
