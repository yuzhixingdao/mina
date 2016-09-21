package com.jy.push.service;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {

	public static void main(String[] args) throws Throwable {
		new MinaServer().init();
    }
	
	public void init(){
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
		acceptor.getSessionConfig().setReadBufferSize(2048);
		
		try {
			
			/**
			 * 添加心跳过滤器
			 */
			KeepAliveFilter heartbeat = new KeepAliveFilter(new KeepAliveMessageFactoryImpl(), IdleStatus.BOTH_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
			heartbeat.setForwardEvent(true);
//			heartbeat.setRequestInterval(5);
			heartbeat.setRequestTimeout(6);
	        acceptor.getFilterChain().addLast("heartbeat", heartbeat);
			
	        acceptor.setHandler(new MinaServer.PushServerHandlerImpl());
			acceptor.bind(new InetSocketAddress(20003));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
		
		public boolean isRequest(IoSession session, Object message) {
			try {
				System.out.println("Server isRequest : " + message.toString());
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		public boolean isResponse(IoSession session, Object message) {
			System.out.println("Server isResponse : " + message.toString());
			if (message.equals("tong"))
				return true;
			return false;
		}

		public Object getRequest(IoSession session) {
			System.out.println("Server getRequest : ...");
			return "ping";
		}

		public Object getResponse(IoSession session, Object message) {
			System.out.println("Server getResponse : " + message.toString());
			return "tong";
		}
		
	}
	
	class PushServerHandlerImpl implements IoHandler{

		public void exceptionCaught(IoSession arg0, Throwable arg1)
				throws Exception {
			
		}

		public void messageReceived(IoSession arg0, Object message)
				throws Exception {
			System.out.println("服务端接收到数据 : " + message.toString());
			
		}

		public void messageSent(IoSession arg0, Object arg1) throws Exception {
			
		}

		public void sessionClosed(IoSession arg0) throws Exception {
			System.out.println("session 关闭。。。。。。。");
		}

		public void sessionCreated(IoSession arg0) throws Exception {
			
		}

		public void sessionIdle(IoSession arg0, IdleStatus arg1)
				throws Exception {
			
		}

		public void sessionOpened(IoSession arg0) throws Exception {
			
		}
		
	}
	
}
