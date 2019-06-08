package cn.tsxxdw.service.myDate;

/**
 * @Author created by dsj
 * @Date 2019/4/24 15:11
 * @Description 时间测试工具类，用于测试不填时间段耗费的时间
 */
public class MytimerUtil {
    private static Mytimer mytimer;

    public static Mytimer get() {
        if (mytimer == null) {
            mytimer = new Mytimer();
        }
        return mytimer;
    }
}
