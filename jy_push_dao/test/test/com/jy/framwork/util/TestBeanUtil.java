package test.com.jy.framwork.util;

import com.jy.framework.util.BeanUtil;
import com.jy.push.entity.JyAccess;


public class TestBeanUtil {

	public static void main(String[] args) {
		
		JyAccess ja = new JyAccess();
		ja.setAccessId("aaaaaaaa");
		ja.setAccessKey("kkkkkkkkkkkkk");
		System.out.println(ja);
		
		JyAccess jt = new JyAccess();
		jt.setId("iiiiiiiiiiiiiii");
		
		BeanUtil.copyProperties(ja, jt, BeanUtil.getNullPropertyNames(ja));
		
		System.out.println(ja);
		System.out.println(jt);
		
	}
	
}
