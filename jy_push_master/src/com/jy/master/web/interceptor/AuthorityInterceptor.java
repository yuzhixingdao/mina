package com.jy.master.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthorityInterceptor implements Interceptor{

	private static final long serialVersionUID = -3924503563980022004L;

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		Object object = session.get("LoginUser");
		
		System.out.println(object);
		
		if(object != null){
			invocation.invoke();
		}
		
		return "loginError";
		
	}

}
