package com.jy.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * @author ShenXiaoqiang
 * @date 2014-9-29
 * 
 */
public class NumberUtil {
	
	private static final DecimalFormat formatThousand = new DecimalFormat("#,###.00");

	public static final String getStr2Scale(Double num) {
		
		if(num != null)
			return new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		
		return null;

	}
	
	public static final BigDecimal getBig2Scale(Double num) {
		
		if(num != null)
			return new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return null;
		
	}

	public static final Double getNum2Scale(Double num) {

		if(num != null)
			return new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		return null;

	}
	
	/**
	 *
	 * @param num
	 * @return
	 */
	public static final String getThousandNum(Double num){
		if(num != null){
			return formatThousand.format(num);
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getStr2Scale(1243532423434.346));
		System.out.println(getNum2Scale(15234243d));
		
		 DecimalFormat df1 = new DecimalFormat("#,###.00");
		 System.out.println(df1.format(85.003));
	}
}
