package com.labs.day8;

public class InsufficientException extends Exception{
    InsufficientException() {
        System.out.println("Insufficient amount Exception");
    }
}

class Bank {
    int balance = 5000;
    public void deposit(int amount) {
        balance = balance + amount;
        System.out.println("The amount deposited is : " + amount);
    }

    public void withdraw(int amount) {

        try {
            if (amount <= balance) {
                balance = balance - amount;
                System.out.println("The amount withdrawn is: " + amount);
            } else {
                throw new InsufficientException();
            }
        }
            catch(InsufficientException e) {
                System.out.println("Insufficient Exception, unable to withdraw");
            }
    }
}

class Test {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.deposit(4000);
        bank.withdraw(10000);

    }
}


// with throws exception

class Bank2 {
    int balance = 5000;
    public void deposit(int amount) {
        balance = balance + amount;
        System.out.println("The amount deposited is : " + amount);
    }

    public void withdraw(int amount) throws InsufficientException {

            if (amount <= balance) {
                balance = balance - amount;
                System.out.println("The amount withdrawn is: " + amount);
            } else {
                throw new InsufficientException();
            }

    }
}

class Test2 {
    public static void main(String[] args) throws InsufficientException {
        Bank2 bank = new Bank2();
        bank.deposit(4000);
        bank.withdraw(10000);

    }
}