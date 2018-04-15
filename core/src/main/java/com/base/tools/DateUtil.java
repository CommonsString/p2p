package com.base.tools;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class DateUtil {
	
	/**
	 * 得到一天的最后一秒钟
	 */
	public static Date endOfDate(Date d){
		return DateUtils.addSeconds(
				DateUtils.addDays(DateUtils.truncate(d, Calendar.DATE), 1), -1);
	}
	
	/**
	 *  两个时间的间隔秒数
	 */
	public static long secondsBetween(Date one, Date two){
		return Math.abs((one.getTime() - two.getTime()) / 1000);
	}
	
}
