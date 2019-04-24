package cn.tsxxdw.myJava;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface Function3_1<T1, T2, T3,R> {

    /**
     * Performs this operation on the given arguments.
     *
     * @param t1 the first input argument
     * @param t2 the second input argument
     * @param t3 the second input argument
     */
    R apply(T1 t1, T2 t2, T3 t3);

}
