package com.jy.framework.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

public class UrlUtil {

	private static final String encoding = "UTF-8";
	
	/**
	 * 发送请求
	 * @param interfaceUrl
	 * @param requestObject
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static String post(String interfaceUrl , Serializable requestObject) throws IOException, ClassNotFoundException{
		String result="";
		InputStream is=null;
		ObjectInputStream ois=null;
		try {
			URL url = new URL(interfaceUrl);
			URLConnection connection = url.openConnection() ;
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(5000);
			
			OutputStream os = connection.getOutputStream() ; 
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(requestObject);
			
			is = connection.getInputStream(); 
			ois = new ObjectInputStream(is);
			result = ois.readUTF();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				is.close();
				ois.close();
			}
		}
		
		return result; 
	}
	
	
	public static String sendData(String url,String xmlData) throws Exception {
		String SERVER_URL = url;
		URL uploadServlet = new URL(SERVER_URL);
		URLConnection servletConnection = uploadServlet.openConnection();
		servletConnection.setUseCaches(false);
		servletConnection.setDoOutput(true);
		servletConnection.setDoInput(true);
		BufferedOutputStream output = new BufferedOutputStream(servletConnection.getOutputStream());
		output.write(xmlData.getBytes(encoding));
		output.close();
		try {
			DataInputStream input = new DataInputStream(servletConnection.getInputStream());
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			byte data[] = (byte[]) null;
			byte b[] = new byte[1024];
			for (int read = 0; (read = input.read(b)) > 0;)
				byteOut.write(b, 0, read);

			data = byteOut.toByteArray();
			String dataString = new String(data , encoding);
			input.close();
			return dataString;
		} catch (Exception e) {
			System.out.println("数据已经发送，但没有返回结果！");
			e.printStackTrace();
			throw e;
		}
	}
	
	public static String doGet(String url) throws Exception {
		String SERVER_URL = url;
		URL uploadServlet = new URL(SERVER_URL);
		URLConnection servletConnection = uploadServlet.openConnection();
		servletConnection.setUseCaches(false);
		servletConnection.setDoOutput(false);
		servletConnection.setDoInput(true);
		
		try {
			DataInputStream input = new DataInputStream(servletConnection.getInputStream());
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			byte data[] = (byte[]) null;
			byte b[] = new byte[1024];
			for (int read = 0; (read = input.read(b)) > 0;)
				byteOut.write(b, 0, read);

			data = byteOut.toByteArray();
			String dataString = new String(data , encoding);
			input.close();
			return dataString;
		} catch (Exception e) {
			System.out.println("数据已经发送，但没有返回结果！");
			e.printStackTrace();
			throw e;
		}
	}
}
