/*
 * Copyright (c) 2010, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package cn.tsxxdw.myJava;


@FunctionalInterface
public interface Consumer2<T1,T2> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T1 t, T2 t2);



}
