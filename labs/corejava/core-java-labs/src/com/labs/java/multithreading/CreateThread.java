package com.labs.java.multithreading;

// Create thread by extending Thread
class Thread1 extends Thread {
      public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Thread name: " + Thread.currentThread().getName() + " - " + i);
        }
    }
}


public class CreateThread {
    public static void main(String[] args) throws InterruptedException {
        Thread1 t1 = new Thread1();  // created  a thread
        t1.start();  //  in runnable state
        t1.setName("t1");
        t1.setPriority(10);
        t1.join();  // waits for the thread to die, it finishes t1 first

        Thread1 t2 = new Thread1();
        t2.start();
        t2.setName("t2");
        t2.setPriority(5);
        t2.join();

        Thread1 t3 = new Thread1();
        t3.start();
        t3.setName("t3");
        t3.setPriority(1);  // priorities also does nto change the order of threads' execution
        t3.join();

    }
}






/*




public class CreateThread  extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}
    class Test {
    public static void main(String args[]) {
        CreateThread createThread = new CreateThread();
        createThread.start();
    }

}

// Java Example by implementing runnable interface

class CreateThreadRunnableInterface implements Runnable {
    public void run() {
        System.out.println("Thread is running : runnable Interface");
    }

    public static void main(String args[]) {
        CreateThreadRunnableInterface createThread2 = new CreateThreadRunnableInterface();
        /*
        If you are not extending the Thread class, your class object would not be treated as a thread object.
        So you need to explicitly create Thread class object.We are passing the object of your class that
        implements Runnable so that your class run() method may execute.
         *//*
Thread t = new Thread(createThread2);
        t.start();
                }
                }  */

