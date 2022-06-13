package com.labs.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloWorldSpringBootApplication  implements CommandLineRunner {

	@Autowired
	ApplicationContext context;


	public static void main(String[] args) {

		System.out.println("Hello Spring Boot before launch");
		// launch spring boot app
		SpringApplication.run(HelloWorldSpringBootApplication.class, args);
		System.out.println("Hello Spring Boot after launch");
	}


	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Spring Boot during launch");
		System.out.println("context: " + context);
		System.out.println("No of Beans: " + context.getBeanDefinitionCount());

		for(String beanname: context.getBeanDefinitionNames()) {
			System.out.println(beanname);
		}

		Greetings greetings = context.getBean("greetings",Greetings.class);
		greetings.setMessage("Hi i am the Greeting bean");
		System.out.println("Greetings Message: " + greetings.getMessage());

		GreetingsService greetingsService = context.getBean("greetingService", GreetingsService.class);
		// here "greetingService" must be the method name which we give while defining the bean at line 53

		greetingsService.showGreetings();

	}

	@Bean
	public Greetings greetings() {   // i dint define GreetingSrvice  as @Componenet, so defining the bean here
		return new Greetings();
	}

	@Bean
	public GreetingsService greetingService() {   // i dint define GreetingSrvice  as @Componenet, so defining the bean here
		return new GreetingsService();
	}

}
