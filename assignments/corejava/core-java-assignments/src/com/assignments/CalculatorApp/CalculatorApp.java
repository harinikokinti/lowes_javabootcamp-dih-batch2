package com.assignments.CalculatorApp;

import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input");
        System.out.println("Enter first number: ");
        int firstNumber = sc.nextInt();
        System.out.println("Enter second number: ");
        int secondNumber = sc.nextInt();

        System.out.println();
        System.out.println("Selet option:");
        System.out.println("Press 1: Add");
        System.out.println("Press 2: Subtract");
        System.out.println("Press 3: Multiply");
        System.out.println("Press 4: Divide");
        System.out.println();

        int number = sc.nextInt();
        int c = 0;

        switch (number) {
            case 1:
                c = firstNumber + secondNumber;
                System.out.println("Output:");
                System.out.println("Result: " + c);
                break;
            case 2:
                c = firstNumber - secondNumber;
                System.out.println("Output:");
                System.out.println("Result: " + c);
                break;
            case 3:
                c = firstNumber * secondNumber;
                System.out.println("Output:");
                System.out.println("Result: " + c);
                break;
            case 4:
                c = firstNumber / secondNumber;
                System.out.println("Output:");
                System.out.println("Result: " + c);
                break;
        }
    }
}
