package cn.tsxxdw.service.mydate;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestDateUtil {
    @Test
    public void test001() {

        String yesterday = DateUtil.currentDateSub(2);//昨天的日期
        String startDate = yesterday + " 00:00:00";
        String endDate = yesterday + " 23:59:59";
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println("----------------------");
        Date curryDate = DateUtil.getCurrentDate();
        Date startDate1 = DateUtil.addMin(curryDate, -32);
        Date endDate1 = DateUtil.addMin(curryDate, -2);
        System.out.println(DateUtil.formatDateTime(DateUtil.getCurrentDate()));
        System.out.println(DateUtil.formatDateTime(startDate1));
        System.out.println(DateUtil.formatDateTime(endDate1));
    }
}
