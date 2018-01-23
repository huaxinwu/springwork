/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodProxy;

/**
 * 代理实体类
 * @author wxh
 * @version $Id: ProxyEntity.java, v 0.1 2017年11月14日 上午10:52:35 wxh Exp $
 */
public class ProxyEntity {
    /** 方法代理  */
    private final MethodProxy methodProxy;
    /** 类类型 */
    private final Class<?>    clazz;
    /** 对象 */
    private final Object      object;
    /** 方法  */
    private final Method      method;
    /** 对象数组  */
    private final Object[]    args;

    /**
     * @param proxy
     * @param class1
     * @param obj
     * @param method
     * @param args
     */
    public ProxyEntity(MethodProxy methodProxy, Class<? extends Object> clazz, Object object,
                       Method method, Object[] args) {
        this.methodProxy = methodProxy;
        this.clazz = clazz;
        this.object = object;
        this.method = method;
        this.args = args;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }

    public Object[] getArgs() {
        return args;
    }

}
