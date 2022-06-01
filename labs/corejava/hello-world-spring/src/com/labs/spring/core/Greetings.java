package com.labs.spring.core;

public class Greetings {
    String message = "Welcome to Spring";

    Greetings() {

    }

    Greetings(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
