package com.assignments.day1;
/*
Question 8: What can be done using one type of loop can also be done using the other two types of loops - True or False.
It is False, since do while executes the statements first before checking the condition
for and while gives same output
do while gives different output for the same logic
 */

// print * in pyramid or triangle shape using for loop

public class Question8 {
    public static void main(String[] args) {
        int i, j, row = 10;
        for (i = 0; i < row; i++) {
            for (j = i; j < row - 1; j++) {
                System.out.print(" ");
            }

            for (j = 1; j <= i; j++) {
                System.out.print("* ");
            }

            System.out.println();
        }
    }
}

// print * in pyramid or triangle shape using while loop

class Question8WhileLoop {
    public static void main(String[] args) {
        int row = 10;
        int i, j;

        i = 0;
        while (i < row) {

            j = i;
            while (j < row - 1) {
                System.out.print(" ");
                j++;
            }

            j = 1;
            while (j <= i) {
                System.out.print("* ");
                j++;
            }

            i++;
            System.out.println();
        }

    }
}

//  print * in pyramid or triangle shape using do while loop, gives different pattern

class Question8DoWhileLoop {
    public static void main(String[] args) {
        int i, j, row = 10;

        i = 0;
        do {
            j = i;
            do {
                System.out.print(" ");
                j++;
            } while (j < row - 1);

            j = 1;
            do {
                System.out.print("* ");
                j++;
            } while (j <= i);

            i++;
            System.out.println();
        } while (i < row);
    }
}