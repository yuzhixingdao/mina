package com.jy.push.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class ClientHandler extends IoHandlerAdapter {
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		System.out.println("客户端接收到数据:" + msg);
		
		if(message != null && message.toString().equals("heartbeat")){
			session.write("{\"accessKey\":\"123456\",\"token\":\"784ed2cd633690923f6a024ceb7fbd2d\"}");
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
	}
	
}
