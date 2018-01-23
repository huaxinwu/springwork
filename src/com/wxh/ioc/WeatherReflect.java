/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.ioc;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射直接注入rain
 * @author wxh
 * @version $Id: WeatherReflect.java, v 0.1 2017年11月13日 下午2:39:07 wxh Exp $
 */
public class WeatherReflect {
    // 模拟Spring容器
    List<Object> objects;

    /**
     * 初始化容器
     */
    public WeatherReflect() {
        objects = new ArrayList<Object>();
    }

    /**
     * 在这里其实最好的做法是先找出带有注解的类，判断带有Entity注解再传入。但是为了方便直接省略了
     * @param object
     * @throws ClassNotFoundException 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    public void getReflect(Object object) throws ClassNotFoundException, IllegalArgumentException,
                                         IllegalAccessException, InstantiationException {
        Class<?> clazz = object.getClass();
        //判断是否带有Entity注解
        if (clazz.isAnnotationPresent(Entity.class)) {
            //获取成员变量
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //判断是否需要注入
                if (field.isAnnotationPresent(Resources.class)) {
                    // 这里先将Rain类加载
                    Class<?> rainClass = Class.forName(field.getType().getName(), false, Thread
                        .currentThread().getContextClassLoader());
                    // 赋给rain
                    field.set(object, rainClass.newInstance());
                    // 最后将已将赋值后的Weather保存进容器
                    objects.add(object);
                }
            }
        }
    }

    /**
     * 获取容器列表
     * @return
     */
    public List<Object> getObjects() {
        return objects;
    }
}
