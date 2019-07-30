package cn.tsxxdw.service.myDate;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author created by dsj
 * @Date 2019/3/6 10:24
 * @Description 计时器，计算时间花费
 */

@Slf4j
public class Mytimer {
    private Map<String, LocalTime> map = new LinkedHashMap<>();

    public int getNumber() {
        return map.size();
    }

    public void add(String describe) {
        map.put(describe, LocalTime.now());
    }

    public void clearAll() {
        map = new LinkedHashMap<>();
    }

    public void getInfo() {
        String oldKey = null;
        for (Map.Entry<String, LocalTime> o : map.entrySet()) {
            String nowKey = o.getKey();
            LocalTime nowTime = o.getValue();
            if (oldKey != null) {
                long secondsDiff = ChronoUnit.SECONDS.between(map.get(oldKey), nowTime);
                long millisDiff = ChronoUnit.MILLIS.between(map.get(oldKey), nowTime);
                log.info(oldKey + "--->" + nowKey + "耗费时间：" + secondsDiff + "秒/" + millisDiff + "毫秒");
            }
            oldKey = nowKey;
        }


    }

  /*  public static void main(String[] args) {
        Mytimer mytimer = new Mytimer();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                mytimer.add(i + "");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        mytimer.getInfo();
    }*/
}
