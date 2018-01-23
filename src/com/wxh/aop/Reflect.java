/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 反射类
 * @author wxh
 * @version $Id: Reflect.java, v 0.1 2017年11月14日 上午10:58:22 wxh Exp $
 */
public class Reflect {
    /** 存入的是方法名以及其注解  */
    Map<String, String> map;
    Map<String, String> clazzMap;

    /**
     * 初始化参数
     * @throws ClassNotFoundException 
     */
    public Reflect() throws ClassNotFoundException {
        map = new HashMap<String, String>();
        clazzMap = new HashMap<String, String>();
        getAnnotationClass();
    }

    /**
     * 这里返回的是已经全部存好的map方便ProxyUtil使用
     * @return
     */
    public Map<String, String> getMap() {
        return map;
    }

    /**
     * 获取一个注解类类型
     * @throws ClassNotFoundException 
     */
    @Test
    public void getAnnotationClass() throws ClassNotFoundException {
        String clazzName = "com.wxh.aop.Player";
        // 这里为了省事直接动态加载了该类
        Class<?> clazz = Class.forName(clazzName, false, Thread.currentThread()
            .getContextClassLoader());
        // 判断是否带有MyAspect注解
        if (clazz.isAnnotationPresent(MyAspect.class)) {
            // 获取该类的所有方法，遍历
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                // 判断是否带有前置通知的注解
                if (method.isAnnotationPresent(Before.class)) {
                    // 获取前置通知注解类
                    Before before = method.getAnnotation(Before.class);
                    // 获取注解的值以及当前类的名字方便调用方法
                    String beforeValue = before.value();
                    // 存入的是方法名和注解名以及执行的顺序，这里为了省事直接就在后面写了
                    map.put(method.getName() + "-" + clazzName + "-" + "before",
                        beforeValue.substring(0, beforeValue.length() - 2));
                }
                // 判断是否带有后置通知注解
                if (method.isAnnotationPresent(After.class)) {
                    // 获取后置通知注解类
                    After after = method.getAnnotation(After.class);
                    // 获取注解的值以及当前类的名字方便调用方法
                    // com.wxh.aop.Music.sing()
                    String afterValue = after.value();
                    // 存入的是方法名和注解名以及执行的顺序，这里为了省事直接就在后面写了
                    // afterSing-com.wxh.aop.Player-after
                    map.put(method.getName() + "-" + clazzName + "-" + "after",
                        afterValue.substring(0, afterValue.length() - 2));
                }
            }
        }
    }

}
