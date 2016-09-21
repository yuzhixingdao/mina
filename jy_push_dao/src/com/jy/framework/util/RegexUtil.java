package com.jy.framework.util;

import java.util.regex.Pattern;

/**
 * Regular expression tools
 * 
 * @author ShenXiaoqiang
 * @date 2014-9-29
 * 
 */
public class RegexUtil {

	/**
	 * Verification email
	 * 
	 * @param input
	 * @return
	 */
	public static Boolean compareEamil(String input) {
		String regex = "[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.][a-zA-Z]+";
		return Pattern.matches(regex, input);
	}

	/**
	 * Verification Number
	 * 
	 * @param input
	 * @return
	 */
	public static Boolean compareNumber(String input) {
		String regex = "[1-9][0-9]+";
		return Pattern.matches(regex, input);
	}

	public static void main(String[] args) {

	}

}
