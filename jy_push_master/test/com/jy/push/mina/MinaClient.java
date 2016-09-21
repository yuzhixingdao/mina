package com.jy.push.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.google.gson.reflect.TypeToken;
import com.jy.framework.util.GsonUtil;
import com.jy.framework.util.UrlUtil;
import com.jy.master.ifc.core.Request;
import com.jy.master.ifc.core.Response;
import com.jy.master.ifc.dto.app.QtRegisterSessionDTO;
import com.jy.master.ifc.dto.app.SpRegisterSessionDTO;


public class MinaClient {

	private static String HOST = "127.0.0.1";
	private static int PORT = 20002;

	public static void main(String[] args) {
		
		mina();
		
		//minaBath();
		
	}
	
	private static class ClientKeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
		
		public boolean isRequest(IoSession session, Object message) {
			System.out.println("请求心跳包信息: " + message);
			return false;
		}

		public boolean isResponse(IoSession session, Object message) {
			System.out.println("响应心跳包信息: " + message);
			return true;
		}

		public Object getRequest(IoSession session) {
			return null;
		}

		public Object getResponse(IoSession session, Object request) {
			return request;
		}
	}
	
	public class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler{

		public void keepAliveRequestTimedOut(KeepAliveFilter keepAliveFilter,
				IoSession session) throws Exception {
			System.out.println("响应心跳超时: 心跳包发送超时处理(及长时间没有发送(接受)心跳包)");
		}
		
	}

	static SpRegisterSessionDTO registerSession(){
		QtRegisterSessionDTO qtDTO = new QtRegisterSessionDTO();
		qtDTO.setAccessKey("gghelp123456");
		qtDTO.setMid("8845256666");

		Request<QtRegisterSessionDTO> request = new Request<QtRegisterSessionDTO>();
		request.setRequestCode("100101");
		request.setData(qtDTO);
		
		String qtData = GsonUtil.toJson(request);
		System.out.println("请求报文："+qtData);
		
		try {
//			String spData = UrlUtil.sendData("http://115.28.229.247:8010/jy_push_master/ifc" , qtData);
			String spData = UrlUtil.sendData("http://127.0.0.1:8787/jy_push_master/ifc" , qtData);
			System.out.println("响应报文："+spData);
			
			Response<SpRegisterSessionDTO> response = new Response<SpRegisterSessionDTO>().fromJson(spData, new TypeToken<Response<SpRegisterSessionDTO>>(){});
			
			if(response != null && response.getResponseCode() != null && response.getResponseCode().equals("1")){
				
				SpRegisterSessionDTO rs = response.getData();
				return rs;
				
			}else{
				System.out.println("响应报文："+spData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static void mina(){

		//注册
		SpRegisterSessionDTO spDTO = registerSession();
		
		// 创建一个非阻塞的客户端程序
		IoConnector connector = new NioSocketConnector();
		// 设置链接超时时间
		connector.setConnectTimeout(30000);
		// 添加过滤器
		connector.getFilterChain().addLast(
				"codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset
						.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
						LineDelimiter.WINDOWS.getValue())));
		
		
//		KeepAliveFilter keepAliveFilter = new KeepAliveFilter(new MinaClient.ClientKeepAliveMessageFactoryImpl(), IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.DEAF_SPEAKER);
//		keepAliveFilter.setForwardEvent(false);
//		keepAliveFilter.setRequestInterval(5);
//		keepAliveFilter.setRequestTimeout(6);
//        connector.getFilterChain().addLast("keepAliveFilter", keepAliveFilter);
		
		// 添加业务逻辑处理器类
		connector.setHandler(new ClientHandler());
		IoSession session = null;
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(spDTO.getIp(), spDTO.getPort()));// 创建连接
			future.awaitUninterruptibly();// 等待连接创建完成
			session = future.getSession();// 获得session
			
			session.write("{\"accessKey\":\"gghelp123456\",\"token\":\""+spDTO.getToken()+"\"}");// 发送消息
			
		} catch (Exception e) {
			System.out.println("客户端链接异常..." + e);
		}

		session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
		connector.dispose();
	}
	
	
	private static void minaBath(){
		
		for (int i = 0; i < 100; i++) {
			
			QtRegisterSessionDTO qtDTO = new QtRegisterSessionDTO();
			qtDTO.setAccessKey("123456");
			qtDTO.setMid("8845256666" + i);

			Request<QtRegisterSessionDTO> request = new Request<QtRegisterSessionDTO>();
			request.setRequestCode("100101");
			request.setData(qtDTO);
			
			SpRegisterSessionDTO spDTO = null;
			
			String qtData = GsonUtil.toJson(request);
			System.out.println("请求报文："+qtData);
			
			try {
				String spData = UrlUtil.sendData("http://192.168.120.161:8080/jy_push_master/ifc" , qtData);
				System.out.println("响应报文："+spData);
				
				Response<SpRegisterSessionDTO> response = new Response<SpRegisterSessionDTO>().fromJson(spData, new TypeToken<Response<SpRegisterSessionDTO>>(){});
				
				if(response != null && response.getResponseCode() != null && response.getResponseCode().equals("1")){
					
					spDTO = response.getData();
					
				}else{
					System.out.println("响应报文："+spData);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			// 创建一个非阻塞的客户端程序
			IoConnector connector = new NioSocketConnector();
			// 设置链接超时时间
			connector.setConnectTimeout(30000);
			// 添加过滤器
			connector.getFilterChain().addLast(
					"codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset
							.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
							LineDelimiter.WINDOWS.getValue())));
			
			// 添加业务逻辑处理器类
			connector.setHandler(new ClientHandler());
			IoSession session = null;
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(spDTO.getIp(), spDTO.getPort()));// 创建连接
				future.awaitUninterruptibly();// 等待连接创建完成
				session = future.getSession();// 获得session
				
				session.write("{\"accessKey\":\"123456\",\"token\":\""+spDTO.getToken()+"\"}");// 发送消息
				
			} catch (Exception e) {
				System.out.println("客户端链接异常..." + e);
			}

			session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
			connector.dispose();
			
		}
		
	}
}
