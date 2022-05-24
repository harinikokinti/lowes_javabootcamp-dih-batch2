package com.labs.java.multithreading;

public class InterThread {
}


class Customer  {
    int amount = 10000;

    synchronized void withdraw(int amount) {
        if(this.amount < amount) {
            try {
                wait() ;
            } catch (Exception e) { System.out.println(e); }  // here if there is no enough amount to withdraw, the thread waits by releasing the lock on the object
        }
        this.amount-= amount;
        System.out.println("Amount Withdraw Completed");
    }

    synchronized void deposit(int amount) {
        this.amount+= amount;
        System.out.println("Amount Deposit Completed");
        notify();   //  it wakes up any thread which is in waiting state
    }
}

class TestInterThreadCooperation {
    public static void main(String args[]) {
        Customer c = new Customer();
        new Thread () {
            public void run() {
                c.withdraw(25000); }
        }.start();

        new Thread () {
            public void run() {
                c.deposit(10000); }
        }.start();

    }

}