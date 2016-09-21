package com.jy.push.service;

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

public class MinaClient2 {
	
	public static void main(String[] args) {
		new MinaClient2().init();
	}
	
	private void init() {
		
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
			ConnectFuture future = connector.connect(new InetSocketAddress("192.168.120.161", 20003));//创建连接
			future.awaitUninterruptibly();// 等待连接创建完成
			session = future.getSession();// 获得session
			
			session.write(" {'token':'14f4836b4dd9e0ad24e54460bee73a2f'}");
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

}
