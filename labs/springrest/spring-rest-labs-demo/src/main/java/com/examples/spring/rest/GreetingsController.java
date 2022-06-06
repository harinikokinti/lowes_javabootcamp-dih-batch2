package com.examples.spring.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
/*
addressability  - @RestController
accessability   - path = "/greetings"
representability - representability by methods to consume (GET) or produce (POST) data
 */

@RestController
public class GreetingsController {

    // http://localhost:8080/spring-rest-labs/greetings
    @RequestMapping(path = "/greetings", method = RequestMethod.GET, produces ={MediaType.TEXT_PLAIN_VALUE} )
    public String greetings() {
        return "Hello Spring REST";
    }
}
