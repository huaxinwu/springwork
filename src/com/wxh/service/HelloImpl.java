/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.service;

/**
 * 实现接口
 * @author wxh
 * @version $Id: HelloImpl.java, v 0.1 2017年11月13日 上午10:49:22 wxh Exp $
 */
public class HelloImpl implements HelloApi {

    /** 
     * 
     */
    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }

}
