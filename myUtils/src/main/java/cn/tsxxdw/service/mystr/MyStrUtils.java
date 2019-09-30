package cn.tsxxdw.service.mystr;

import cn.tsxxdw.service.mydate.MyDateUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MyStrUtils {
    public static final char UNDERLINE = '_';
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

    /**
     *
     * @return String 获取一个字符，日期+随即字符串
     */
    public static String getIdDateStr(String type){
        StringBuffer sb=new StringBuffer();
        sb.append(MyDateUtil.getDateStr("yyyyMMddHHmmss"));
        sb.append("-");
        sb.append( UUID.randomUUID().toString().replaceAll("-", "").substring(0,10));
        sb.append("_");
        sb.append(type);
        return  sb.toString();
    }


    //驼峰转下划线
    public static String camelToUnderline(String param, Integer charType) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
            }
            if (charType == 2) {
                sb.append(Character.toUpperCase(c));  //统一都转大写
            } else {
                sb.append(Character.toLowerCase(c));  //统一都转小写
            }


        }
        return sb.toString();
    }

    //下划线转驼峰
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        Boolean flag = false; // "_" 后转大写标志,默认字符前面没有"_"
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                flag = true;
                continue;   //标志设置为true,跳过
            } else {
                if (flag == true) {
                    //表示当前字符前面是"_" ,当前字符转大写
                    sb.append(Character.toUpperCase(param.charAt(i)));
                    flag = false;  //重置标识
                } else {
                    sb.append(Character.toLowerCase(param.charAt(i)));
                }
            }
        }
        return sb.toString();
    }



    public static List<String> replaceStrForInt(String str,String replaceStr,int startInt,int endInt){
        List<String>list=Lists.newArrayList();
        for (int i=startInt;i<endInt;i++){
            list.add(    str.replace(replaceStr,i+""));
        }
        return list;
    }
}
