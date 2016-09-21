package com.jy.push.mina.session;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.jy.push.dict.DictStatus;
import com.jy.push.entity.JyMinaSession;
import com.jy.push.exception.PushException;
import com.jy.slave.service.IfcService;
import com.jy.slave.service.SlaveService;

public class SessionManager {
	
	private Logger logger = Logger.getLogger(Logger.class);
	
	private static Map<String , IoSession> sessions = new HashMap<String , IoSession>();
	private static Map<String , String> sessionIds = new HashMap<String , String>();
	
	private SlaveService slaveService;
	private IfcService ifcService;
	
	public void setIfcService(IfcService ifcService) {
		this.ifcService = ifcService;
	}
	public static Map<String, IoSession> getSessions() {
		return sessions;
	}
	public static Map<String, String> getSessionIds() {
		return sessionIds;
	}
	public SessionManager(){
		/*new Thread(){
			public void run(){
				while(true){
					logger.info("sending heartbreak info .session count = " + sessions.values().size());
					for( IoSession session : sessions.values()  ){
						session.write("life\n");
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();*/
	}
	public void sendAllMsg(String msg){
		for( IoSession session : sessions.values()  ){
			session.write(msg);
		}
	}
	public void sendMsgToVeh(String msg,String vcode){
		for( IoSession session : sessions.values()  ){
			session.write(msg);
		}
	}
	public IoSession getSession(String sessionId){
		IoSession session = sessions.get(sessionId);
		if(session == null ){
			logger.info("session "+sessionId + " is not exist . ");
		}
		return session ; 
	}
	
	public IoSession addSession(String sessionId ,String key, IoSession ioSession){
		if(sessions == null){
			logger.info("session to be added can't be null . ");
			throw new PushException("session to be added can't be null . ");
		}
		if(sessionId == null ){
			logger.error(" sessionId can't be null . ");
			throw new PushException("sessionId can't be null .");
		}
		if(sessions.containsKey(sessionId)){
			logger.info("there already have a session identified by id : " + sessionId + " , and it will be overrite . " );
		}
		
		if (sessions.get(key) != null)
			sessions.get(key).close(true);
		
		sessionIds.put(sessionId, key);
		sessions.put(key, ioSession);
		
		String locaIp  = ((InetSocketAddress)ioSession.getLocalAddress()).getAddress().getHostAddress();
		int locaPort = ((InetSocketAddress)ioSession.getLocalAddress()).getPort();
		String remoteIp  = ((InetSocketAddress)ioSession.getRemoteAddress()).getAddress().getHostAddress();
		int remotePort = ((InetSocketAddress)ioSession.getRemoteAddress()).getPort();
		
		JyMinaSession minaSession = slaveService.getMinaSessionBySessionId(key);
		if (minaSession == null) {
			minaSession = new JyMinaSession();
			minaSession.setConnectDate(new Date());
			minaSession.setCreateDate(new Date());
			minaSession.setDelFlag("0");
			minaSession.setSessionId(key);
			minaSession.setSessionState(DictStatus.MINA_SESSION_AVAILABLE);
			minaSession.setToken(key);
			
			minaSession.setRemoteIp(remoteIp);
			minaSession.setRemotePort(remotePort);
			minaSession.setLocaIp(locaIp);
			minaSession.setLocaPort(locaPort);
			
			slaveService.addMinaSession(minaSession);
		} else {
			minaSession.setConnectDate(new Date());
			minaSession.setSessionState(DictStatus.MINA_SESSION_AVAILABLE);
			minaSession.setRemoteIp(remoteIp);
			minaSession.setRemotePort(remotePort);
			minaSession.setLocaIp(locaIp);
			minaSession.setLocaPort(locaPort);
			
			slaveService.updateMinaSession(minaSession);
		}
		
		System.out.println("sessions count >>>>> " + sessions.size());
		
		return ioSession;
	}
	
	
	public IoSession removeSession(String sessionId, IoSession session){
		if(sessionId == null ){
			logger.error(" sessionId can't be null . ");
			throw new PushException("sessionId can't be null .");
		}
		
		String key = sessionIds.get(sessionId);
		if (key != null && !"".equals(key)) {
			
			String cip  = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
			int cport = ((InetSocketAddress)session.getRemoteAddress()).getPort();
			
			if (sessions.get(key) != null) {
				String sip = ((InetSocketAddress)sessions.get(key).getRemoteAddress()).getAddress().getHostAddress();
				int sport = ((InetSocketAddress)sessions.get(key).getRemoteAddress()).getPort();
				
				if (cip != null && !"".equals(cip) && sip != null && !"".equals(sip) && cport == sport) {
					sessions.get(key).close(true);
					
					sessions.remove(key);
					
					slaveService.removeSession(key);//移除
					ifcService.downlineSession(key);//下线
				}
			}
			
			sessionIds.remove(sessionId);
		}
		
		return null;
	}
	
	/**
	 * 推送消息到单个设备
	 * @param token
	 * @return
	 */
	public static boolean pushToken(String token, String data){
		
		if(token != null && sessions.containsKey(token)){
			IoSession is = sessions.get(token);
			if(is != null && is.isConnected()){
				is.write(data);
				System.out.println("session >>> " + is + " sessionId >>> " + token);
				System.out.println("pushToken >>>>>>>>>>>>>>>>>>>>>>> " + data);
				System.out.println("------------------------------------------");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断Session是否存在
	 * @param sessionId
	 * @return
	 */
	public static boolean containsSession(String sessionId) {
		if(sessionId != null && sessions.containsKey(sessionId)){
			return true;
		}
		return false;
	}
	
	public void setSlaveService(SlaveService slaveService) {
		this.slaveService = slaveService;
	}
	
	
}
