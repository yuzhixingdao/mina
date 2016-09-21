package com.jy.framework.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

/**
 * @author ShenXiaoqiang
 * @date 2014-10-28
 *
 */
public class FileUtil {

	/**
	 * 文件批量上传
	 * @param uploadFiles
	 * @param uploadFileNames
	 * @param uploadUrl
	 * @return 文件名称
	 */
	public static final List<String> batchUpload(List<File> uploadFiles, List<String> uploadFileNames, String uploadUrl){
		
		if(uploadFiles != null && uploadFileNames != null && uploadUrl != null){
			
			List<String> fillName = new ArrayList<String>();
			
			for(int i=0; i<uploadFiles.size(); i++){
				
				InputStream in = null;
				BufferedInputStream bis = null;
				
				OutputStream out = null;
				BufferedOutputStream bos = null;
				
				try {
					
					UUID uuid = UUID.randomUUID();
					String id = uuid.toString().replace("-", "");
					
					int lastIndexOf = uploadFileNames.get(i).lastIndexOf(".");
					String suffix = uploadFileNames.get(i).substring(lastIndexOf);
					
					in = new FileInputStream(uploadFiles.get(i));
					bis = new BufferedInputStream(in);
					
					File file = new File(uploadUrl + File.separator);
					file.mkdirs();
					fillName.add(id + suffix);
					
					out = new FileOutputStream(uploadUrl + File.separator + id + suffix);
					bos = new BufferedOutputStream(out);
					
					int len = 0; 
					byte[] bt = new byte[1024];
					
					while((len = bis.read(bt)) > 0){
						bos.write(bt, 0, len);
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(bos != null){
						try {
							bos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					if(bis != null){
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				
			}
			
			return fillName;
			
		}
		
		return null;
		
	}
	
	/**
	 * 文件上传
	 * @param uploadFile
	 * @param uploadFileName
	 * @param uploadUrl
	 * @return
	 */
	public static final String upload(File uploadFile, String uploadFileName, String uploadUrl){
		
		if(uploadFile != null && uploadFileName != null && uploadUrl != null){
			
			String fillName = "";
			
			InputStream in = null;
			BufferedInputStream bis = null;
			
			OutputStream out = null;
			BufferedOutputStream bos = null;
			
			try {
				
				UUID uuid = UUID.randomUUID();
				String id = uuid.toString().replace("-", "");
				
				int lastIndexOf = uploadFileName.lastIndexOf(".");
				String suffix = uploadFileName.substring(lastIndexOf);
				
				in = new FileInputStream(uploadFile);
				bis = new BufferedInputStream(in);
				
				File file = new File(uploadUrl);
				file.mkdirs();
				fillName = id + suffix;
				
				out = new FileOutputStream(uploadUrl + id + suffix);
				bos = new BufferedOutputStream(out);
				
				int len = 0;
				byte[] bt = new byte[1024];
				
				while((len = bis.read(bt)) > 0){
					bos.write(bt, 0, len);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(bos != null){
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(bis != null){
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			return fillName;
			
		}
		
		return null;
		
	}

	/**
	 * 文件上传
	 * @param image
	 * @param postFix
	 * @param uploadUrl
	 * @return 文件名称
	 */
	public static final String upload(BufferedImage image, String postFix, String uploadUrl){
		if(image != null && postFix != null){
			UUID uuid = UUID.randomUUID();
			String id = uuid.toString().replace("-", "");
			
			OutputStream out = null;
			try {
				out = new FileOutputStream(uploadUrl + id + "." + postFix);
				ImageIO.write(image, postFix, out);
				
				return id + "." + postFix;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(out != null){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		return null;
	}
	
	public static final Boolean deleteFile(String filePath){
		
		File file = new File(filePath);
		if(file.exists()){
			return file.delete();
		}
		
		return false;
		
	}
	
}
