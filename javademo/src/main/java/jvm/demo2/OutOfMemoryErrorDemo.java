package jvm.demo2;

import cn.tsxxdw.vo.ResultVo;

public class OutOfMemoryErrorDemo {

    /**
     * 条件：设置了最大内存 -Xmx20m -Xms20m
     * 结论：
     * eden 内存在不断的变大，然后又被回收
     * s1 区域变化了一次
     * old 变化： 不断增加，增加比例相对慢
     *
     */
    public void addObject1() {
        try {
            Thread.sleep(20000);//先休息5秒再执行
            int max = 1000000;
            int i = 0;
            while (i < max) {
                new ResultVo<>();
                i++;
                Thread.sleep(1);
                System.out.println(i);
            }
            System.out.println("task compile");
            Thread.sleep(500000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
