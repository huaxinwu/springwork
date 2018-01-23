/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.aop;

/**
 * 唱歌前的准备
 * 表明这是一个切点类
 * @author wxh
 * @version $Id: Player.java, v 0.1 2017年11月14日 上午10:24:12 wxh Exp $
 */
@MyAspect
public class Player {
    /**
     * 前置通知，当sing方法被调用的时候该方法会被在它之前调用
     */
    @Before("com.wxh.aop.Music.sing()")
    public void beforeSing() {
        System.out.println("开始唱歌前的准备");
    }

    /**
     * 在调用sing方法之后再来调用该方法
     */
    @After("com.wxh.aop.Music.sing()")
    public void afterSing() {
        System.out.println("唱完之后开始评分");
    }
}
