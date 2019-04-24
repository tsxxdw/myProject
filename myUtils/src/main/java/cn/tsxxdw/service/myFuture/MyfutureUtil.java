package cn.tsxxdw.service.myFuture;

import cn.tsxxdw.bean.vo.excel.ExcelEntityVo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author created by dsj
 * @Date 2019/4/24 17:01
 * @Description future公共服务
 */
public class MyfutureUtil<T> {
    /**
     * @param future
     * @param numberOfCycles
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static <T> T getFutureDate(Future<T> future, int numberOfCycles) throws Exception {
        if (future.isDone()) {
            return future.get();
        } else {
            Thread.sleep(200);
            if (numberOfCycles > 5) {
                throw new Exception("Data acquisition timeout");
            }
            return getFutureDate(future, ++numberOfCycles);
        }
    }

    public static <T> T getFutureDate(Future<T> future) throws Exception {
        if (future.isDone()) {
            return future.get();
        } else {
            return getFutureDate(future, 1);
        }

    }
}
