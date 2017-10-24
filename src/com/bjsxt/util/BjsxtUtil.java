package com.bjsxt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class BjsxtUtil {

	//获取当前时间，字符串格式(yyyy-MM-dd)
	public static String nowdatestring(){
		//创建SimpleDateFormat
		//SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前时间
		//Date date = new Date();
		//获取转型后的日期格式
		//String newdate = sf.format(date);
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	
	//获取当前时间,时间格式(yyyy-MM-dd)
	public static Date createDate(){
		//获取当前日期
		Date date = new Date();
		//声明日期格式
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//把当前日期转换成日期字符串（"yyyy-MM-dd"）
		String strdate = simpleDateFormat.format(date);
		Date nowdate = null;
		try {
			//把字符串转成时间类型，格式yyyy-MM-dd
			nowdate = simpleDateFormat.parse(strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nowdate;
	}
	
	//把日期类型的字符串(yyyy-MM-dd)转成日期格式(yyyy-MM-dd)
	public static Date toDate(String strdate){
		//声明日期格式
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date newdate = null;
		try {
			newdate = simpleDateFormat.parse(strdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newdate;
	}
	
	
	
	//创建uuid
	public static String createuuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
}
