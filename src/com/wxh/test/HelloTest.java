/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wxh.service.HelloApi;

/**1.接口和实现都开发好了，那如何使用Spring IoC容器来管理它们呢？这就需要配置文件
 * 2.那如何获取IoC容器并完成我们需要的功能呢？首先应该实例化一个IoC容器，然后从容器中获取需要的对象，然后调用接口完成我们需要的功能
 * @author wxh
 * @version $Id: HelloTest.java, v 0.1 2017年11月13日 上午10:57:12 wxh Exp $
 */
public class HelloTest {

    @Test
    public void testHelloWorld() {
        // 1.实例化一个Ioc容器
        ApplicationContext context = new ClassPathXmlApplicationContext(
            "resource/xml/helloworld.xml");
        // 2.从容器获取bean，Ioc管理bean
        HelloApi helloApi = context.getBean("hello", HelloApi.class);
        System.out.println(System.getProperty("user.dir"));
        // 3.执行业务逻辑
        helloApi.sayHello();
    }
}
