package cn.tsxxdw.service.mystr;

import cn.tsxxdw.service.mydate.MyDateUtil;
import org.apache.poi.ss.usermodel.DateUtil;

import java.util.UUID;

public class MyStrUtils {
    /**
     *
     * @return String 获取一个字符，日期+随即字符串
     */
    public static String getIdDateStr(){
        StringBuffer sb=new StringBuffer();
        sb.append(MyDateUtil.getDateStr("yyyyMMddHHmmss"));
        sb.append("-");
        sb.append( UUID.randomUUID().toString().replaceAll("-", "").substring(0,10));
        return  sb.toString();
    }
}
