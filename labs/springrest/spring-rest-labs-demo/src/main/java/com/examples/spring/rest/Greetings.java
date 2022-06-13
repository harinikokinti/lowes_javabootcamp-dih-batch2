package com.examples.spring.rest;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Greetings {
    String message;
    String greeter;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGreeter() {
        return greeter;
    }

    public void setGreeter(String greeter) {
        this.greeter = greeter;
    }
}
