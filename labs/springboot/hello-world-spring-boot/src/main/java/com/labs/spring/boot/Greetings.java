package com.labs.spring.boot;

import org.springframework.stereotype.Component;

//@Component
public class Greetings {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
