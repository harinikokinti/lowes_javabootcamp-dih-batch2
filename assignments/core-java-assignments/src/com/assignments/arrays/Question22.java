package com.assignments.arrays;

//22) Add Two Matrices

public class Question22 {
    public static void main(String[] args) {
        // create 2 matrices
        int a[][] = {{10, 20, 30}, {40, 50, 60}, {70, 80, 90}};
        int b[][] = {{10, 20, 30}, {40, 50, 60}, {70, 80, 90}};

        // create a 2d array to store the result
        int c[][] = new int[3][3];

        System.out.println("The Addition of two matrices: ");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = a[i][j] + b[i][j];
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }
}
