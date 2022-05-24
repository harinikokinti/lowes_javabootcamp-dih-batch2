package com.labs.java.day7;

public class MultiDimensionalArray {
    public static void main(String[] args) {
        int array[][] = {{1,2,3}, {10,20,30}, {100,200,300}};  // delcare and initialise 2nd with 3 rows and 3 columns

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/*
o/p:
1 2 3
10 20 30
100 200 300
 */