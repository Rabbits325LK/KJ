package com.keepjob.sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 日历工具类
 * @author liangkang
 *
 */
public class FullCalendarUtils {
	
	private FullCalendarUtils(){}
	
	/**
	 * 遍历Mavin集合通过专家编号获取专家名称 
	 * @param mavins
	 * @param mavinCode
	 * @return
	 */
	/*public static String resultMavinNameByMavinCode(List<Mavin> mavins, String mavinCode){
		String result = "";
		for(Mavin mavin : mavins){
			if(mavin.getUniqueCode().equals(mavinCode)){
				result = mavin.getName();
				break;
			}
		}
		return result;
	}*/
	
	/**
	 * 遍历Mavin集合通过专家编号获取诊断机构编号
	 * @param mavins
	 * @param mavinCode
	 * @return
	 */
	/*public static String resultDiagnosisOrganCodeByMavinCode(List<Mavin> mavins, String mavinCode){
		String result = "";
		for(Mavin mavin : mavins){
			if(mavin.getUniqueCode().equals(mavinCode)){
				result = mavin.getOrganCode();
				break;
			}
		}
		return result;
	}*/
	
	/**
	 * 通过值班日期判读是否过去，返回不同颜色作为展示区分
	 * @param onDutyDate
	 * @return
	 * @throws ParseException
	 */
	public static String resultBackgroundColor(Date onDutyDate) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//System.out.println(df.parse(df.format(date)).getTime()+"-"+df.parse(df.format(onDutyDate)).getTime());
		if(df.parse(df.format(date)).getTime() < df.parse(df.format(onDutyDate)).getTime()){
			return "#3399FF";
		}else if(df.parse(df.format(date)).getTime() == df.parse(df.format(onDutyDate)).getTime()){
			return "#66CC00";
		}else{
			return "#999999";
		}
	}
}
