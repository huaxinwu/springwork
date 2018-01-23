/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.aop;

/**
 * cglib代理测试
 * 思路就是先通过反射获取到切点类，然后将用注解标注的方法名以及注解里面的值存入一个map，最后在建立一个类用来处理map
 * @author wxh
 * @version $Id: CglibProxyTest.java, v 0.1 2017年11月14日 下午2:13:24 wxh Exp $
 */
public class CglibProxyTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Music music = new Music();
        CglibProxy cglibProxy = new CglibProxy(music);
        /**
         * 输出结果
         * 开始唱歌前的准备
         * 测试代理的人唱歌
         * 唱完之后开始评分
         */
        ((Music) cglibProxy.getProxy()).sing("测试代理的人");

    }
}
