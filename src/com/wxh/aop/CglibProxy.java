/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * cglib动态代理
 * glib依赖asm包，在目标类的基础上生成一个子类，然后通过子类来实现在目标方法调用的时候实现前置或者后置通知
 * 属于编译器织入，因为是通过子类生成字节码然后进行调用
 * @author wxh
 * @version $Id: CglibProxy.java, v 0.1 2017年11月14日 上午10:31:22 wxh Exp $
 */
public class CglibProxy implements MethodInterceptor {
    private Object    target;
    private ProxyUtil proxyUtil;

    /**
     * 初始化参数
     * @param target
     * @throws ClassNotFoundException 
     */
    public CglibProxy(Object target) throws ClassNotFoundException {
        this.target = target;
        proxyUtil = new ProxyUtil();
    }

    /**
     * 获取一个代理
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public <T> T getProxy() {
        return (T) new Enhancer().create(this.target.getClass(), this);
    }

    /**
     * 根据一个类类型获取一个代理
     * @param clazz
     * @return
     */
    @SuppressWarnings({ "unchecked", "static-access" })
    public <T> T getProxy(Class<?> clazz) {
        return (T) new Enhancer().create(this.target.getClass(), this);
    }

    /** 
     * 拦截处理，生成一个对象
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                                                                                        throws Throwable {

        ProxyEntity proxyEntity = new ProxyEntity(proxy, this.target.getClass(), obj, method, args);
        return proxyUtil.generateEntity(proxyEntity);
    }
}
