package cn.tsxxdw;

import java.io.*;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;

public class Register {
    static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

//    public static void main(String[] args) {
//        String start = "2019-01-01"; // 开始时间
//        String end = "2030-01-01";// 注册码失效时间
//        String cpuId=getCpuId();
//        String prewith = MD5(cpuId+start);
//        String endwith = base64Date(start,end);
//        System.out.println("=====注册码=====");
//        System.out.println(prewith+endwith);
//    }

    public String getResult(String cpuId){
        String start = "2019-01-01"; // 开始时间
        String end = "2030-01-01";// 注册码失效时间
        String prewith = MD5(cpuId+start);
        String endwith = base64Date(start,end);
        System.out.println("=====注册码=====");
        System.out.println(prewith+endwith);
        return prewith+endwith;
    }
    public static String base64Date(String start,String end){
        String str = start+"~"+end;
        String res = null;
        try {
            res = new String(Base64.getEncoder().encode(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static String getCpuId() {
        try {
            Process process;
            (process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"})).getOutputStream().close();
            Scanner sc;
            (sc = new Scanner(process.getInputStream())).next();
            return MD5(sc.next());
        } catch (IOException var1) {
            var1.printStackTrace();
            return null;
        }
    }

    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("UTF-8"));

            StringBuilder ret = new StringBuilder(bytes.length * 2);
            for (int i=0; i<bytes.length; i++) {
                ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
                ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
            }
            return ret.toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
