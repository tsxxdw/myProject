package cn.tsxxdw.service.myDate;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtil {
	
	static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM","HH:mm:ss"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek(Date data) {
		return formatDate(data, "E");
	}

	/**
	 * 得到日期星期几
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekNumCH(Date date) {
		int []arr = {7,1,2,3,4,5,6};
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		return arr[calendar.get(Calendar.DAY_OF_WEEK)-1];
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return DateUtils.parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}

	/**
	 * 获取相差的分钟
	 * @param sDate
	 * @param eDate
	 * @return
	 */
	public static long diffMinutes(Date sDate, Date eDate) {
		long t = eDate.getTime()-sDate.getTime();
		return t/(60*1000);
	}


	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastSeconds(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/1000;
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		if(null == before || null == after){
			return -1;
		}
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 获取当前日期时间
	 * @return
     */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 两个日期之间相差的秒数
	 * @param startDate
	 * @return
     */
	public static int calLastedTime(Date startDate) {
		long a = new Date().getTime();
		long b = startDate.getTime();
		int c = (int)((a - b) / 1000);
		return c;
	}

	/**
	 * 查询某一天的前一天日期（yyyy-MM-dd）
	 *
	 */
	public static String getOneDateBefore(String specifiedDay){
		Calendar c = Calendar.getInstance();
		Date date = null;
		try{
			date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
		}catch (Exception e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day-1);
		String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return dayBefore;
	}


	/**
	 * 判断当天是周几
	 *
	 */
	public static int getNowWeek(){
		Date today = new Date();
		Calendar c= Calendar.getInstance();
		c.setTime(today);
		int weekday=c.get(Calendar.DAY_OF_WEEK) - 1;
		return weekday;
	}


	/**
	 * 判断当天是一个月的几号
	 *
	 */
	public static int getNowMonth(){
		Calendar c = Calendar.getInstance();
		int datenum=c.get(Calendar.DATE);
		return datenum;
	}

	/**
	 * 判断某一天是不是当月的最后一天
	 *
	 */
	public static boolean isLastMonthDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(calendar.get(Calendar.DATE)==calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
			return true;
		else
			return false;
	}

	/**
	 * 判断时间是否在两个时间内
	 * @param strDateBegin
	 * @param strDateEnd
     * @return
     */
	public static boolean isInDates(String strDateBegin,String strDateEnd){
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
		Date myDate = new Date();
		String strDate = String.valueOf(sd.format(myDate));

		int strDateH = Integer.parseInt(strDate.substring(0,2));
		int strDateM = Integer.parseInt(strDate.substring(3,5));
		int strDateS = Integer.parseInt(strDate.substring(6,8));

		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0,2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3,5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6,8));

		int strDateEndH = Integer.parseInt(strDateEnd.substring(0,2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3,5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6,8));

		if((strDateH>=strDateBeginH && strDateH<=strDateEndH)){
			if(strDateH>strDateBeginH && strDateH<strDateEndH){
				return true;
			}else if(strDateH==strDateBeginH && strDateM>strDateBeginM && strDateH<strDateEndH){
				return true;
			}else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateS>strDateBeginS && strDateH<strDateEndH){
				return true;
			}else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateS==strDateBeginS && strDateH<strDateEndH){
				return true;
			}else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM<strDateEndM){
				return true;
			}else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM && strDateS<strDateEndS){
				return true;
			}else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM && strDateS==strDateEndS){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	
	
	/**
	 * 判断时间是否在两个时间内 只有时分
	 * @param strDateBegin
	 * @param strDateEnd
     * @return
     */
	public static boolean isInDatesTime(String strDateBegin,String strDateEnd){
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
		Date myDate = new Date();
		String strDate = String.valueOf(sd.format(myDate));

		int strDateH = Integer.parseInt(strDate.substring(0,2));
		int strDateM = Integer.parseInt(strDate.substring(3,5));

		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0,2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3,5));

		int strDateEndH = Integer.parseInt(strDateEnd.substring(0,2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3,5));

		if((strDateH>=strDateBeginH && strDateH<=strDateEndH)){
			if(strDateH>strDateBeginH && strDateH<strDateEndH){
				return true;
			}else if(strDateH==strDateBeginH && strDateM>strDateBeginM && strDateH<strDateEndH){
				return true;
			}else if(strDateH==strDateBeginH && strDateM==strDateBeginM && strDateH<strDateEndH){
				return true;
			}else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM<strDateEndM){
				return true;
			}else if(strDateH>strDateBeginH && strDateH==strDateEndH && strDateM==strDateEndM){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/**
	 * 在指定的日期上减指定的天数
	 * @param date
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date minusDate(Date date,long day) throws ParseException {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time -= day; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}
	
	/**
	 * 在指定的日期上加指定的天数
	 * @param date
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date addDate(Date date,long day){
		try{
			long time = date.getTime(); // 得到指定日期的毫秒数
			day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
			time += day; // 相加得到新的毫秒数
			return new Date(time); // 将毫秒数转换成日期
		}catch(Exception e){
			logger.error("给指定日期增加天数异常");
			return null;
		}
	}
	
	/**
	 * 在指定的日期上加指定的小时数
	 * @param date
	 * @param hour
	 * @return
	 * @throws ParseException
	 */
	public static Date addHour(Date date,long hour) throws ParseException{
		long time = date.getTime();
		time = time + hour * 60 * 60 * 1000;
		return  new Date(time);
	}
	
	/**
	 * 在指定的日期上加指定的天数
	 * @param date
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Date addMin(Date date,long min){
		try{
			long time = date.getTime(); // 得到指定日期的毫秒数
			min = min*60*1000; // 要加上的分钟数转换成毫秒数
			time += min; // 相加得到新的毫秒数
			return new Date(time); // 将毫秒数转换成日期
		}catch(Exception e){
			logger.error("给指定日期增加天数异常");
			return null;
		}
	}

	/**
     * 当前日期减指定的天数
     * @param day
     * @return
     */
    public static String currentDateSub(int day){
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
        return dft.format(date.getTime());
    }
    
    
    public static String getDayText(String keyDate) {
		String result = "";
		Date date = DateUtil.getCurrentDate();
		if (keyDate.equals(DateUtil.formatDate(date))) {
			result = "今天";
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);// 日
			if (keyDate.equals(DateUtil.formatDate(calendar.getTime()))) {
				result = "明天";
			}
		}
		return result;
	}

    public static Long getLongTime(Long temp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(temp));
		int minute = calendar.get(Calendar.MINUTE);// 分
		minute = Long.valueOf(Math.round(minute / 10D) * 10).intValue();
		calendar.set(Calendar.MINUTE, minute);
		return calendar.getTime().getTime();
	}

    /**
     * 将日期字符串转换为日期型 格式为“2017-12-29”
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(strDate);
        return date;
    }


	/**
	 * 将日期字符串转换为日期型 格式为“2017-12-29”
	 *
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date parseStrToDate(String strDate,String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = sdf.parse(strDate);
		return date;
	}

	/**
	 * 星期英文转化
	 * @param week
	 * @return
	 */
	public  static int getWeekNum(String week){
    	switch (week.toUpperCase()){
			case "MONDAY":
				return 1;
			case "TUESDAY":
				return 2;
			case "WEDNESDAY":
				return 3;
			case "THURSDAY":
				return 4;
			case "FRIDAY":
				return 5;
			case "SATURDAY":
				return 6;
			case "SUNDAY":
				return 7;
		}
		return 0;
	}

	/** 获取当前时间的开始，2018-09-12 00:00:00
	 * @return
	 */
	public static Date getCurrentDateBegin(){
		Date currentEndDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentEndDate);
		cal.set(Calendar.AM_PM, 0);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return  cal.getTime();
	}
}
