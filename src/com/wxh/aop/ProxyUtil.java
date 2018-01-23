/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.aop;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 代理工具类
 * 处理这个Cglib里面的MethodInterceptor接口中的intercept方法的具体类
 * @author wxh
 * @version $Id: ProxyUtil.java, v 0.1 2017年11月14日 上午10:43:31 wxh Exp $
 */
public class ProxyUtil {

    Reflect reflect;

    /**
     * map key的分离
     * @param key
     * @return
     */
    private String[] mapKeyDivision(String key) {
        String[] strs = new String[10];
        // 注解下面的方法
        strs[0] = key.substring(0, key.indexOf("-"));
        // 注解所在的类
        strs[1] = key.substring(key.indexOf("-") + 1, key.lastIndexOf("-"));
        // 是before还是after
        strs[2] = key.substring(key.lastIndexOf("-") + 1, key.length());
        return strs;
    }

    /**
     * 处理map，遍历进行逻辑处理
     * @param proxyEntity
     * @param methodName
     * @return
     * @throws Throwable 
     */
    private void invokeMap(ProxyEntity proxyEntity, String methodName) throws Throwable {
        // 截取括号
        String proxyMethodValue = proxyEntity
            .getMethod()
            .toString()
            .substring(proxyEntity.getMethod().toString().lastIndexOf(" ") + 1,
                proxyEntity.getMethod().toString().indexOf("("));
        // 获取所有存储的方法
        Map<String, String> methodMap = reflect.getMap();
        // 遍历map
        for (Map.Entry<String, String> map : methodMap.entrySet()) {
            if (map.getValue().equals(proxyMethodValue)) {
                String[] strings = mapKeyDivision(map.getKey());
                // methodName:"before" or "after"
                if (strings[2].equals(methodName)) {
                    Class<?> clazz = Class.forName(strings[1], false, Thread.currentThread()
                        .getContextClassLoader());
                    Method method = clazz.getDeclaredMethod(strings[0]);
                    // 反射调用方法,返回对象
                    method.invoke(clazz.newInstance(), null);
                }
            }
        }
    }

    /**
     * 处理后置通知
     * @param proxyEntity
     * @return
     * @throws Throwable 
     */
    private Object doAfter(ProxyEntity proxyEntity) throws Throwable {
        Object object = proxyEntity.getMethodProxy().invokeSuper(proxyEntity.getObject(),
            proxyEntity.getArgs());
        invokeMap(proxyEntity, "after");
        return object;
    }

    /**
     *  初始化参数
     * @throws ClassNotFoundException 
     */
    public ProxyUtil() throws ClassNotFoundException {
        reflect = new Reflect();
    }

    /**
     * 根据代理类生成一个对象
     * @param proxyEntity
     * @return
     * @throws Throwable 
     */
    public Object generateEntity(ProxyEntity proxyEntity) throws Throwable {
        invokeMap(proxyEntity, "before");
        // 处理后置通知
        return doAfter(proxyEntity);
    }

}
