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

import java.util.Objects;


@FunctionalInterface
public interface Consumer3<T1,T2,T3> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T1 t,T2 t2,T3 t3);



}
