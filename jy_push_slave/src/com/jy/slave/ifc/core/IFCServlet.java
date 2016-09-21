package com.jy.slave.ifc.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

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
	private static final long serialVersionUID = 1L;
	private WebApplicationContext wac ;
	
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
		Date requestTime = new Date();

		Request requestObject = null;
		String requestCode = null ; 
		String token = null ; 
		
		String accessId = UUID.randomUUID().toString().replaceAll("-", "");
		try {
			
			requestData = getRequestData(request);
			
			System.out.println(requestData);
			
			requestObject = gson.fromJson(requestData, Request.class);
			if(requestObject!=null){
				requestCode = requestObject.getRequestCode();
				
				token = requestObject.getToken();
				r.setToken(token);
				
				try{
					if(requestCode!=null){
						IFCService busService = (IFCService)wac.getBean("ifc_"+requestCode);
						if(busService!=null){
							
							System.out.println(requestData);
							
							r = busService.execute(requestData);
							r.setRequestCode(requestCode);
							r.setToken(token);
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
				System.out.println(r);
				writeResponse(response , r , accessId  );
			} catch (IOException e) {
				e.printStackTrace();
			}
			try{
				//doIfcLog(r, requestTime, dto, requestObject, requestCode, token , accessId);
			}catch(Exception ex){
				//logger.info("写入接口日志出错，异常："+ex.getMessage());
				ex.printStackTrace();
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
			data.append(new String(buffer , 0 , length , "UTF-8" ));
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
	private void writeResponse(HttpServletResponse response ,Response data ,String accessId ) throws IOException{
		Gson gson = GsonUtil.createGson();
		response.setCharacterEncoding("UTF-8");
		
		String json = gson.toJson(data);
		OutputStream os = response.getOutputStream() ; 
		os.write(json.getBytes("UTF-8"));
		os.flush();
		os.close();
	}
}

