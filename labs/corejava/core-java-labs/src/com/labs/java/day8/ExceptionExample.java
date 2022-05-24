package com.labs.java.day8;

public class ExceptionExample {
    public static void main(String[] args) {

        // case 1 :  exception not occur
        try {
            int c = 10/5;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Finally called");
        }

        // case 2 : exception occur but not handled
        try {
            int c = 10/5;
        }
        finally {
            System.out.println("Finally called");
        }

        // case 3: exception occur and handled
        try {
            int c = 10/0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Finally called");
        }


    }
}
