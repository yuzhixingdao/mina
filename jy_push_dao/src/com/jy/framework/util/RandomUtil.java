package com.jy.framework.util;

import java.util.Random;

/**
 * @author ShenXiaoqiang
 * @date 2014-9-16
 * 
 */
public class RandomUtil {

	private static Random random = new Random();

	/**
	 * 
	 * @return
	 */
	public static String get6BNum() {

		String validateCode = "";

		for (int i = 0; i < 6; i++) {
			validateCode += random.nextInt(10);
		}

		return validateCode;
	}

	/**
	 * 
	 * @param leng
	 * @return
	 */
	public static String getNum(Integer leng) {

		String validateCode = "";

		for (int i = 0; i < leng; i++) {
			validateCode += random.nextInt(10);
		}

		return validateCode;
	}

	/**
	 * 
	 * @return
	 */
	public static String getUppercase() {
		int nextInt = random.nextInt(26);
		nextInt = nextInt + 65;
		char abc = (char) nextInt;
		return abc + "";
	}

	/**
	 * @return
	 */
	public static String getUppercase(Integer leng) {

		if (leng != null) {

			StringBuffer zm = new StringBuffer();

			for (int i = 0; i < leng; i++) {
				int nextInt = random.nextInt(26);
				nextInt = nextInt + 65;
				char abc = (char) nextInt;
				zm.append(abc);
			}

			return zm.toString();

		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println((int) 'A');
		System.out.println((int) 'Z');
		System.out.println(random.nextInt(1));
		System.out.println(getNum(3));
		System.out.println(getUppercase());
		System.out.println(getUppercase(5));
	}

}
