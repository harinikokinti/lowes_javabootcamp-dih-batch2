package com.labs.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloWorldSpring {

    public static void main(String[] args) {

//        Greetings greetings = new Greetings();
//        System.out.println(greetings.getMessage());
//
//        Greetings greetings2 = new Greetings("Welcome from argument constructor");
//        System.out.println(greetings2.getMessage());

        // Step 1 : Create instance for IoC container
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-config.xml");
        //ApplicationContext ctx = new FileSystemXmlApplicationContext("src/beans-config.xml");  // can pass the file path

        System.out.println(ctx);
        System.out.println("no of beans : " + ctx.getBeanDefinitionCount());

        // STEP 2: Access beans from Container
        Greetings greetings = ctx.getBean("greetings", Greetings.class);
        System.out.println(greetings.getMessage());

        Greetings greetings2 = ctx.getBean("greetings2", Greetings.class);
        System.out.println(greetings2.getMessage());







    }
}
