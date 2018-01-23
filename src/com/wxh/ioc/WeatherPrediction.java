/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.ioc;

/**
 * IOC实现测试
 * @author wxh
 * @version $Id: WeatherPrediction.java, v 0.1 2017年11月13日 下午2:52:36 wxh Exp $
 */
public class WeatherPrediction {

    public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException,
                                          IllegalAccessException, InstantiationException {
        Weather weather = (Weather) SpringStart.initSpring();
        // 结果输出"正在下雨"
        weather.weatherRain();
    }

}
