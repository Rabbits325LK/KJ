package com.keepjob.home.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.keepjob.common.Constant;
import com.keepjob.common.util.DateUtils;
import com.keepjob.common.util.EncryptUtils;
import com.keepjob.common.util.IPUtils;
import com.keepjob.common.util.VerifyAccountUtil;
import com.keepjob.common.util.json.JSONMessage;
import com.keepjob.core.members.Members;
import com.keepjob.core.members.MembersHandler;

@Controller
@RequestMapping("/main")
public class MainController {

	@Autowired
	private Gson gson;
	@Autowired
	private MembersHandler membersHandler;
	
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String toIndexView(HttpSession session, HttpServletRequest request){
		return "login";
	}
	
	@RequestMapping(value = "/register.json", method = RequestMethod.POST)
	@ResponseBody
	public String toRegister(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "realName", required = true) String realName,
			@RequestParam(value = "sex", required = false) String sex,
			@RequestParam(value = "age", required = false) Integer age){
		try {
			Members record = new Members();
			record.setAge(age);
			record.setEmail(email);
			record.setRealName(realName);
			record.setPassword(password);
			record.setPhone(phone);
			record.setSex(sex);
			this.membersHandler.saveOrUpdate(null, record);
			return gson.toJson(JSONMessage.createSuccessMessage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/checkEmail.json", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmail(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "email", required = true) String email){
		try {
			if(!VerifyAccountUtil.checkEmail(email)){
				return gson.toJson(JSONMessage.createFailedMessage("邮箱地址格式有误，请检查."));
			}else if(this.membersHandler.existsEmail(email)){
				return gson.toJson(JSONMessage.createSuccessMessage());
			}else{
				return gson.toJson(JSONMessage.createFailedMessage("邮箱已被注册，请更换."));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping(value = "/checkPhone.json", method = RequestMethod.POST)
	@ResponseBody
	public String checkPhone(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "phone", required = true) String phone){
		try {
			if(!VerifyAccountUtil.checkMobileNumber(phone)){
				return gson.toJson(JSONMessage.createFailedMessage("手机号码格式有误，请检查."));
			}else if(this.membersHandler.existsPhone(phone)){
				return gson.toJson(JSONMessage.createSuccessMessage());
			}else{
				return gson.toJson(JSONMessage.createFailedMessage("手机号码已被注册，请更换."));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
		}
	}
	
	@RequestMapping( value = "/login.json", method = RequestMethod.POST, produces = Constant.APPLICATION_JSON)
	@ResponseBody
	public String toLogin(HttpServletRequest request, 
			@RequestParam(value = "searchCode", required = true)String searchCode,
			@RequestParam(value = "password", required = true)String password){
		try {
			HttpSession session = request.getSession(true);
			Members members = this.membersHandler.getMembers(VerifyAccountUtil.isMobileOrEmail(searchCode), searchCode, null);
			if(null != members){
				System.out.println("PASSWORD:"+members.getPassword()+"--->"+EncryptUtils.encode(StringUtils.trimToEmpty(password)));
				if(members.getPassword().equals(EncryptUtils.encode(StringUtils.trimToEmpty(password)))){
					members.setLastLoginDate(DateUtils.getCurrentDateTime());
					members.setLastLoginIp(IPUtils.getIPV4(request));
					members.setPassword(null);
					this.membersHandler.saveOrUpdate(null, members);
					
					session.setAttribute(Constant.MEMBERS_KEY, members);
					
					return gson.toJson(JSONMessage.createSuccessMessage());
				}else{
					return gson.toJson(JSONMessage.createFailedMessage("密码不正确"));
				}
			}else{
				return gson.toJson(JSONMessage.createFailedMessage("用户不存在"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return gson.toJson(JSONMessage.createFailedMessage(e.getMessage()));
			// TODO: handle exception
		}
	}
	
	/**
	 * 用于测试上传
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/uploadTest.html", method = RequestMethod.GET)
	public String toUploadTestView(HttpSession session, HttpServletRequest request){
		return "uploadTest";
	}
	
	@RequestMapping(value = "/webTemplate.html", method = RequestMethod.GET)
	public String toTemplateAddView(HttpSession session, HttpServletRequest request){
		return "webtemplate/templateAdd";
	}
}
