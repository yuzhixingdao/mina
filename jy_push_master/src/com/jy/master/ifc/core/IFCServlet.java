package com.jy.master.ifc.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.jy.framework.util.GsonUtil;

public class IFCServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2290908620476223118L;
	
	private WebApplicationContext wac ;
	private String encoding = "UTF-8";
	
	@Override
	public void init() throws ServletException{
		super.init();
		ServletContext servletContext = super.getServletContext();
		wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		Response r = new Response();
		r.setResponseCode("0");
		
		Gson gson = GsonUtil.createGson();
		String requestData;

		Request requestObject = null;
		String requestCode = null ; 
		String token = null ; 
		
		try {
			
			requestData = getRequestData(request);
			
			System.out.println("请求数据:" + requestData);
			
			requestObject = gson.fromJson(requestData, Request.class);
			if(requestObject!=null){
				requestCode = requestObject.getRequestCode();
				
				token = requestObject.getToken();
				r.setToken(token);
				
				try{
					if(requestCode!=null){
						IFCService busService = (IFCService)wac.getBean("ifc_"+requestCode);
						if(busService!=null){
							
							r = busService.execute(requestData);
							r.setRequestCode(requestCode);
							
						}else{
							r.setResponseCode("0");
							r.setErrorMessage("请求码错误，没有请求码：" + requestCode);
						}
					}else{
						r.setResponseCode("0");
						r.setErrorMessage("无法获取请求参数信息");
					}
				}catch(Exception e){
					r.setResponseCode("0");
					r.setErrorMessage("服务器异常:"+e.getMessage());
					e.printStackTrace();
				}
			}else{
				r.setResponseCode("0");
				r.setErrorMessage("no input request data");
			}
		} catch (IOException e1) {
			r.setResponseCode("0");
			r.setErrorMessage("服务器异常:接收参数失败"+e1.getMessage());
			e1.printStackTrace();
		}catch (Throwable e1) {
			r.setResponseCode("0");
			r.setErrorMessage(e1.getMessage());
			e1.printStackTrace();
		} finally{
			try {
				System.out.println("返回结果：" + GsonUtil.toJson(r));
				writeResponse(response , r);
			} catch (IOException e) {
				e.printStackTrace();
			}
 		}
		
	}
	
	/**
	 * Get请求
	 */
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
		doPost(request,response);
	}
	
	/**
	 * 从输入流中获取请求数据
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private String getRequestData(HttpServletRequest request) throws IOException{
		InputStream is = request.getInputStream();
		byte[] buffer = new byte[2048];
		int length = 0 ; 
		StringBuffer data = new StringBuffer();
		while((length = is.read(buffer))!=-1){
			data.append(new String(buffer , 0 , length , encoding));
		}
		is.close();
		return data.toString();
	}
	/**
	 * 响应结果
	 * @param response
	 * @param data
	 * @throws IOException
	 */
	private void writeResponse(HttpServletResponse response ,Response data) throws IOException{
		Gson gson = GsonUtil.createGson();
		response.setCharacterEncoding(encoding);
		
		String json = gson.toJson(data);
		OutputStream os = response.getOutputStream() ; 
		os.write(json.getBytes(encoding));
		os.flush();
		os.close();
	}
}

