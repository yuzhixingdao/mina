package com.jy.master.web.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class MUserAction extends BaseAction {

	private String loginName;
	private String password;

	public String login() {
		if(loginName != null && !loginName.equals("")
				&& password != null && !password.equals("")){
			
			Map<String, String> loginUser = new HashMap<String, String>();
			loginUser.put("loginName", loginName);
			loginUser.put("password", password);
			
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("LoginUser", loginUser);
			
			return "success_login";
		}
		
		super.msg = "用户名或密码错误";
		return "error_login";
	}
	
	public String exitSystem(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("LoginUser", null);
		return "success_exitSystem";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
