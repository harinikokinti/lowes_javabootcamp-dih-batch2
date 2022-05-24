package com.labs.java.multithreading;

public class ThreadDemo {
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());
        System.out.println(Thread.currentThread().getState().name());
        System.out.println(Thread.currentThread().isDaemon());
    }
}
