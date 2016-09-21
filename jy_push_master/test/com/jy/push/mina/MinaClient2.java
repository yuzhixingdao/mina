package com.jy.push.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
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

public class MinaClient2 {
	
	public static void main(String[] args) {
		new MinaClient2().init();
	}
	
	private void init() {
		
		//注册
		SpRegisterSessionDTO spDTO = registerSession();
		
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeout(30000);
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
		
		/**
		 * 添加心跳过滤器
		 */
		KeepAliveFilter heartbeat = new KeepAliveFilter(new KeepAliveMessageFactoryImpl(), IdleStatus.BOTH_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
		heartbeat.setForwardEvent(true);
		heartbeat.setRequestInterval(5);
		heartbeat.setRequestTimeout(6);
		connector.getFilterChain().addLast("heartbeat", heartbeat);
        
		// 添加业务逻辑处理器类
		connector.setHandler(new ClientHandler());
		IoSession session = null;
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(spDTO.getIp(), spDTO.getPort()));//创建连接
			future.awaitUninterruptibly();// 等待连接创建完成
			session = future.getSession();// 获得session
			
			session.write("{\"accessKey\":\"123456\",\"token\":\""+spDTO.getToken()+"\"}");// 发送消息
			
		} catch (Exception e) {
			System.out.println("客户端链接异常..." + e);
		}

		session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
		connector.dispose();
	}
	
	class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
		
		public boolean isRequest(IoSession session, Object message) {
			System.out.println("Client isRequest : " + message.toString());
			if(message != null && message.toString().equals("ping")){
				return true;
			}
			return false;
		}

		public boolean isResponse(IoSession session, Object message) {
			System.out.println("Client isResponse : " + message.toString());
			if(message != null && message.toString().equals("tong")){
				return true;
			}
			return false;
		}

		public Object getRequest(IoSession session) {
			System.out.println("Client getResponse : ...");
			return "tong";
		}

		public Object getResponse(IoSession session, Object message) {
			System.out.println("Client getResponse : " + message.toString());
			return "tong";
		}
	}
	
	class ClientHandler extends IoHandlerAdapter {
		
		@Override
		public void messageReceived(IoSession session, Object message)
				throws Exception {
			String msg = message.toString();
			System.out.println("客户端接收到数据:" + msg);
		}

		@Override
		public void exceptionCaught(IoSession session, Throwable cause)
				throws Exception {
		}
		
	}

	SpRegisterSessionDTO registerSession(){
		QtRegisterSessionDTO qtDTO = new QtRegisterSessionDTO();
		qtDTO.setAccessKey("123456");
		qtDTO.setMid("8845256666");

		Request<QtRegisterSessionDTO> request = new Request<QtRegisterSessionDTO>();
		request.setRequestCode("100101");
		request.setData(qtDTO);
		
		String qtData = GsonUtil.toJson(request);
		System.out.println("请求报文："+qtData);
		
		try {
			String spData = UrlUtil.sendData("http://192.168.120.161:8080/jy_push_master/ifc" , qtData);
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
	
}
