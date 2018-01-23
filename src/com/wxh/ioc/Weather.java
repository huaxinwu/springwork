/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.ioc;

/**
 * 获取到的天气数据
 * @author wxh
 * @version $Id: Weather.java, v 0.1 2017年11月13日 下午2:36:37 wxh Exp $
 */
@Entity
public class Weather {

    // 这里在后面通过反射直接注入rain
    @Resources
    Rain rain;

    public void weatherRain() {
        rain.rain();
    }

}
