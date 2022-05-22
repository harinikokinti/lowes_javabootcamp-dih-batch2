package com.labs.day8;


public class NestedExceptions {

    public static void doSomething() throws Exception {
        doSomethingElse();
    }

    public static void doSomethingElse() throws Exception {
        doSomethingSomethingElse();
    }

    public static void doSomethingSomethingElse() throws Exception {
        throw new Exception();
    }


    public static void main(String[] args) {
        try {
            doSomething();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
