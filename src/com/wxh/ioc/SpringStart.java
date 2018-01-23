/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.ioc;

/**
 * 模拟spring启动类
 * @author wxh
 * @version $Id: SpringStart.java, v 0.1 2017年11月13日 下午2:57:41 wxh Exp $
 */
public class SpringStart {

    private SpringStart() {
    }

    /**
     * 模拟Spring启动过程，这一步其实可以单独写一个类，这一步是容器该做的，而我们并不需要去管
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object initSpring() throws ClassNotFoundException, IllegalArgumentException,
                                     IllegalAccessException, InstantiationException {
        // 启动的时候就需要加载的容器
        WeatherReflect reflect = new WeatherReflect();
        // 扫描类注解后new操作然后进行下一步
        Weather weather = new Weather();
        // 将其类里面的变量进行new操作并放入容器
        reflect.getReflect(weather);
        // 获取一个对象,取第一条数据
        Object object = reflect.getObjects().get(0);
        return object;
    }
}
