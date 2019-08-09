package cn.tsxxdw.service.mydate;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MyDateUtil {
    public static String getDateStr(String dateFormat) {
        LocalDateTime arrivalDate = LocalDateTime.now();
        try {
            if (StringUtils.isBlank(dateFormat)) {
                dateFormat = "yyyy-MM-dd HH:mm:ss";
            }
            DateTimeFormatter format = DateTimeFormatter.ofPattern(dateFormat);
            String landing = arrivalDate.format(format);
            return landing;
        } catch (DateTimeException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Date  转 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateConvertToLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }


    /**
     * 将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeConvertToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));

    }
    public static SimpleDateFormat getSimpleDateFormat2DateSecondNoTag() {
        return (SimpleDateFormat)threadLocal_datetime_second_notag.get();
    }

    public static void printlnLocalDateTime() {
        System.out.println("date:" + LocalDateTime.now());
    }

    public static SimpleDateFormat getSimpleDateFormat2yyyyMMdd() {
        return (SimpleDateFormat) threadLocal_yyyyMMdd.get();
    }

    private static final ThreadLocal<SimpleDateFormat> threadLocal_yyyyMMdd = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> threadLocal_datetime_second_notag = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        }
    };
}
