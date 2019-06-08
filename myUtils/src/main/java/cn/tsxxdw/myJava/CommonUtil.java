/*
 *
 *    WingSing CONFIDENTIAL
 *    _____________________
 *
 *    [2014] - [2015] WingSing Supply Chain Management Co. (Shenzhen) Ltd.
 *    All Rights Reserved.
 *
 *    NOTICE: All information contained herein is, and remains the property of
 *    WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers, if
 *    any. The intellectual and technical concepts contained herein are proprietary
 *    to WingSing Supply Chain Management Co. (Shenzhen) Ltd. and its suppliers and
 *    may be covered by China and Foreign Patents, patents in process, and are
 *    protected by trade secret or copyright law. Dissemination of this information
 *    or reproduction of this material is strictly forbidden unless prior written
 *    permission is obtained from WingSing Supply Chain Management Co. (Shenzhen)
 *    Ltd.
 */
package cn.tsxxdw.myJava;

import cn.tsxxdw.vo.ResultVo;
import org.springframework.scheduling.annotation.Async;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class CommonUtil {
    public static <E> void foreach(Iterable<? extends E> iterable,int index, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(iterable, "The container shouldn't be null");
        Objects.requireNonNull(action, "The action shouldn't be null");
        for (E e : iterable) {
            action.accept(index++, e);
        }
    }



    public static <E> void foreach(Iterable<? extends E> iterable, int index, int number, BiConsumer<Integer, ? super E> action) {
        Objects.requireNonNull(iterable, "The container shouldn't be null");
        Objects.requireNonNull(action, "The action shouldn't be null");
        for (E e : iterable) {
            action.accept(index, e);
            index += number;
        }
    }

    /**
     * 循环责任链处理器，一旦有一个处理器失败则都失败
     *
     * @param iterable
     * @param action
     * @param <E>
     * @return
     */
    public static <E> ResultVo handlerForeach(Iterable<? extends E> iterable, BiFunction<Integer, ? super E, ResultVo> action) {
        Objects.requireNonNull(iterable, "The container shouldn't be null");
        Objects.requireNonNull(action, "The action shouldn't be null");
        int index = 0;
        for (E e : iterable) {
            ResultVo resultVo = action.apply(index++, e);
            if ((resultVo.isFail())) {
                return resultVo;
            }
        }
        return ResultVo.createSimpleSuccessResult();
    }

}
