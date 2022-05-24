package com.labs.java.multithreading;

// Create thread by implemeing Runnable
class Sample implements Runnable {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread name: " + Thread.currentThread().getName() + " - " + i);
        }
    }
}

public class CreateThreadRunnable implements Runnable {
    public static void main(String[] args) {
        Sample sample = new Sample();
        // Approach 1
        Thread t1 = new Thread(sample);
        t1.setName("t1");
        t1.start();

        // Approach 2
        Thread t2 = new Thread(new Sample());
        t2.setName("t2");
        t2.start();

        // Approach 3
        Thread t3 = new Thread(new CreateThreadRunnable());  // here CreateThreadRunnable must implement Runnable
        t3.setName("t3");
        t3.start();

        // Approach 4
        Thread t4 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread name: " + Thread.currentThread().getName() + " - " + i);
                }
            }
        });
        t4.setName("t4");
        t4.start();


    }

    @Override
    public void run() {
        System.out.println("thread running");
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread name: " + Thread.currentThread().getName() + " - " + i);
        }

    }
}
