package com.jy.push.mina.handler.impl;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.json.JSONException;
import org.json.JSONObject;

import com.jy.push.mina.handler.PushServerHandler;
import com.jy.push.mina.session.SessionManager;
import com.jy.push.service.PushConfigService;
import com.jy.slave.service.SlaveService;

public class PushServerHandlerImpl implements PushServerHandler {

	private Logger logger = Logger.getLogger(this.getClass());
	
	private SessionManager sessionManager;
	private PushConfigService pushConfigService;
	private SlaveService slaveService;
	
	public void setPushConfigService(PushConfigService pushConfigService) {
		this.pushConfigService = pushConfigService;
	}
	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}
	public void setSlaveService(SlaveService slaveService) {
		this.slaveService = slaveService;
	}
	
	public void exceptionCaught(IoSession session, Throwable arg1)
			throws Exception {
		
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		logger.info("session received ... ");
		
		try {
			logger.info("recieve message 接收数据 : " + message);
			validate(session, message);
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.info("e : " + e.getMessage());
			logger.info("e : " + e.getLocalizedMessage());
			logger.info("e : " + e.getStackTrace());
		}
	}

	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("message has sent : " + message);
	}

	public void sessionClosed(IoSession session) throws Exception {
		logger.info("session closed ... ");
		sessionManager.removeSession(String.valueOf(session.getId()), session);
	}

	public void sessionCreated(IoSession session) throws Exception {
		logger.info("session created ... ");
//		session.getConfig().setIdleTime(IdleStatus.READER_IDLE, pushConfigService.getHeartbeat());
	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		logger.info("session idle ... ");
//		if (status == IdleStatus.READER_IDLE) {
//			session.write("heartbeat");
//			long lastReadTime = session.getLastReadTime();
//			long lastWriteTime = session.getLastWriteTime();
//			long time = Math.abs((lastReadTime - lastWriteTime));
//			if(time > (1000 * 5)){
//				session.close(true);
//			}
//			System.out.println("lastReadTime : " + lastReadTime);
//			System.out.println("lastWriteTime : " + lastWriteTime);
//			System.out.println("time : " + time);
//		}
	}

	public void sessionOpened(IoSession session) throws Exception {
		logger.info("session opened ... ");
	}

	private void validate(IoSession session, Object message) {
		
		if(message != null){
			try {
				
				if(message.toString().indexOf("{") > -1 && message.toString().indexOf("}") > -1){
					
					JSONObject json = new JSONObject(message.toString());
					
					if(!json.isNull("type")){
						
						String type = json.get("type").toString();
						
						if (type.equals("01")) {
							boolean isToken = json.isNull("token");
							if(!isToken){
								String token = json.getString("token");
								if(token != null && !token.trim().equals("")){
									
									IoSession sessison = sessionManager.getSession(token);
									
									if (sessison == null) {
										logger.info("sessid not exist");
										sessionManager.addSession(String.valueOf(session.getId()), token, session);
										logger.info("session reset create");
									} else {
										int sport = ((InetSocketAddress)sessison.getRemoteAddress()).getPort();
										String shostAddress = ((InetSocketAddress)sessison.getRemoteAddress()).getAddress().getHostAddress();
										int cport = ((InetSocketAddress)session.getRemoteAddress()).getPort();
										String chostAddress = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
										
										if (sport == cport && shostAddress != null && chostAddress != null && shostAddress.equals(chostAddress)) {
											logger.info("sessin is not null");
										} else {
											logger.info("sessid not exist");
											sessionManager.addSession(String.valueOf(session.getId()), token, session);
											logger.info("session reset create");
										}
									}
									
								}else{
									session.write("{\"ret_code\":201}");//token 不存在
								}
								
							}else{
								session.write("{\"ret_code\":200}");//token key null
							}
						} else if (type.equals("02")) {
							if(message != null){
								if(!json.isNull("messageId")){
									Object object = json.get("messageId");
									System.out.println("messageId >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + object);
									if(object != null){
										String messageId = (String)object;
										slaveService.updateMessageReceive(messageId);
									}
								}
							}
						}
						
					}
					
				}
				
			} catch (JSONException e) {
				logger.info("e : " + e.getMessage());
				session.write("{\"ret_code\":100}");//send message null
				e.printStackTrace();
			}
			
		}else{
			session.write("{\"ret_code\":10}");//send message null
		}
		
	}

}
