package com.labs.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpring {
        public static void main(String[] args) {
            // Step 1 : Create instance for IoC container
            ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-config.xml"); //  creates Ioc container 
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
