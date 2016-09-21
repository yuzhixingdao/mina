package com.jy.push.service;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.json.JSONException;
import org.json.JSONObject;

import com.jy.push.entity.JyAccess;
import com.jy.push.mina.session.SessionManager;
import com.jy.slave.service.JyAccessService;
import com.jy.slave.service.SlaveService;

public class PushServer {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PushServer.class.getName());
	
	private PushConfigService pushConfigService;
	private SessionManager sessionManager;
	private IoHandler pushServerHandler;
	
	private JyAccessService jyAccessService;
	private SlaveService slaveService;
	
	private static final String HEARTBEATREQUEST = "ping";
    private static final String HEARTBEATRESPONSE = "tong";
    
	public void init() {
		
		/**
		// ����һ����������server�˵�Socket
		IoAcceptor acceptor = new NioSocketAcceptor();
		// ���ù�������ʹ��Mina�ṩ���ı����з����������
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),
						LineDelimiter.WINDOWS.getValue(),
						LineDelimiter.WINDOWS.getValue())));
		
		acceptor.getSessionConfig().setReadBufferSize(2048);
		
		try {
			
//			KeepAliveFilter heartbeat = new KeepAliveFilter(new KeepAliveMessageFactoryImpl(), IdleStatus.BOTH_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
//			heartbeat.setForwardEvent(false);
//			heartbeat.setRequestInterval(5);
//			heartbeat.setRequestTimeout(6);
//	        acceptor.getFilterChain().addLast("heartbeat", heartbeat);
			
	        // ���߼�������
	        acceptor.setHandler(pushServerHandler);
			// �󶨶˿�
			acceptor.bind(new InetSocketAddress(pushConfigService.getPushPort()));
			
			//ע��Slaer
			slaveService.registerMinaServicer(InetAddress.getLocalHost().getHostAddress(), pushConfigService.getPushPort());
			
		} catch (NumberFormatException e) {
			logger.error("Push Server startup fail . ");
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Push Server startup fail . ");
			e.printStackTrace();
		}
		logger.error("Push Server startup success , listening port : " + pushConfigService.getPushPort());
		*/
		
		IoAcceptor acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"),LineDelimiter.WINDOWS.getValue(),LineDelimiter.WINDOWS.getValue())));
		acceptor.getSessionConfig().setReadBufferSize(2048);
		
		try {
			
//			/**
//			 * �������������
//			 */
//			KeepAliveFilter heartbeat = new KeepAliveFilter(new KeepAliveMessageFactoryImpl(), IdleStatus.BOTH_IDLE, KeepAliveRequestTimeoutHandler.CLOSE);
//			heartbeat.setForwardEvent(true);
//			heartbeat.setRequestInterval(5);
//			heartbeat.setRequestTimeout(6);
//	        acceptor.getFilterChain().addLast("heartbeat", heartbeat);
			
	        acceptor.setHandler(pushServerHandler);
			acceptor.bind(new InetSocketAddress(pushConfigService.getPushPort()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
		
		public boolean isRequest(IoSession session, Object message) {
			try {
				System.out.println("Server isRequest : " + message.toString());
				if(message != null){
					JSONObject json = new JSONObject(message.toString());
					boolean isRet = json.isNull("token");
					if(!isRet){
						String token = json.getString("token");
						if(token != null && !token.trim().equals("")){
							
							IoSession sessison = sessionManager.getSession(token);
							if (sessison != null) {
								logger.info("sessin is not null");
							} else {
								logger.info("sessid not exist");
								sessionManager.addSession(String.valueOf(session.getId()), token, session);
								logger.info("session ���´���");
							}
							
						}
					}
				}
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
	
	private class KeepAliveRequestTimeoutHandlerImpl implements KeepAliveRequestTimeoutHandler{

		public void keepAliveRequestTimedOut(KeepAliveFilter keepAliveFilter,
				IoSession session) throws Exception {
			logger.info("��Ӧ������ʱ: ���������ͳ�ʱ����(����ʱ��û�з���(����)������)");
		}
		
	}
	
	/**
	 * �����֤
	 * @param session
	 * @param message {"accessKey":""}
	 * @return
	 * @throws JSONException
	 */
	private void validate(IoSession session, Object message) throws Exception{
		
		JSONObject json = new JSONObject(message.toString());
		
		boolean isAccessKey = json.isNull("accessKey");
		if(!isAccessKey){
			String accessKey = json.getString("accessKey");
			
			if(accessKey == null || "".equals(accessKey) ){
				session.write("{\"ret_code\":101}");//accessKey����
			}
			
			//��ѯ���ݿ��Ƿ����
			JyAccess access = jyAccessService.getAccess(accessKey);
			if(access == null){
				session.write("{\"ret_code\":102}");//accessKey�����ڣ�û��ע��
			}
		}else{
			session.write("{\"ret_code\":100}");//accessKey key null
		}
		
		boolean isToken = json.isNull("token");
		if(!isToken){
			String token = json.getString("token");
			if(token != null && !token.trim().equals("")){
				
				IoSession sessison = sessionManager.getSession(token);
				if (sessison != null) {
					logger.info("sessin is not null");
					logger.info("session ���´���");
				} else {
					logger.info("sessid not exist");
					sessionManager.addSession(String.valueOf(session.getId()), token, session);
					logger.info("session ���´���");
				}
				
			}else{
				session.write("{\"ret_code\":201}");//token ������
			}
			
		}else{
			session.write("{\"ret_code\":200}");//token key null
		}
		
	}

	
	public void setPushConfigService(PushConfigService pushConfigService) {
		this.pushConfigService = pushConfigService;
	}

	public void setSessionManager(SessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	public void setPushServerHandler(IoHandler pushServerHandler) {
		this.pushServerHandler = pushServerHandler;
	}

	public void setJyAccessService(JyAccessService jyAccessService) {
		this.jyAccessService = jyAccessService;
	}

	public void setSlaveService(SlaveService slaveService) {
		this.slaveService = slaveService;
	}


	
}
