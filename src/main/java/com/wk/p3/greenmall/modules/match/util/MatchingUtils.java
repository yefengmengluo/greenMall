package com.wk.p3.greenmall.modules.match.util;

import java.util.Date;

import com.wk.p3.greenmall.common.utils.StringUtils;

public class MatchingUtils {
	public static Long getTimeStemp(String time){
		Date date = new Date(time);
		Long timeStemp = date.getTime();
		return timeStemp;
	}
	/**
	 * 范围匹配计算
	 * 计算公式：result1=(infoValue1+infoValue2)/2
	 * 		  result2=(infoValue1+infoValue2)/2
	 * 		  finalResult=(|result1-result2|)/(result1+result2)/2*weight
	 * @param infoValue1
	 * @param infoValue2
	 * @param matchValue1
	 * @param matchValue2
	 * @param weight 
	 */
	public static Double getGradeForScope(String infoValue1, String infoValue2, String matchValue1, String matchValue2, Integer weight) {
		Double result1=Math.abs(Double.parseDouble(infoValue2)+Double.parseDouble(infoValue2));
		Double result2=Math.abs(Double.parseDouble(matchValue1)+Double.parseDouble(matchValue2));
		Double finalResult=(Math.abs(result1-result2))/(result1+result2)/2*weight;
		return finalResult;
	}
	public static Double getGradeForSingle(Double infoValue, Double matchValue, Integer weight) {
		Double finalResult=(Math.abs(infoValue-matchValue))/(infoValue+matchValue)/2*weight;
		return finalResult;
	}
	public static Double getGradeForAddress(String infoValue1, String infoValue2, String infoValue3, String matchValue1,
			String matchValue2, String matchValue3,Integer weight) {
		Double finalResult= 0.0;
		if(StringUtils.isBlank(infoValue1)|StringUtils.isBlank(matchValue1)){
			return finalResult;
		}
		if(infoValue1.equals(matchValue1)){
			finalResult++;
		}
		if(infoValue2.equals(matchValue2)){
			finalResult++;
			finalResult++;
		}
		if(infoValue3.equals(matchValue3)){
			finalResult++;
			finalResult++;
			finalResult++;
		}
		return finalResult*weight;
	}
}
