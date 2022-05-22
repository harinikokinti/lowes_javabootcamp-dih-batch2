package com.empapp;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;
import com.empapp.service.EmployeeService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeManagementMain {
    public static void main(String[] args) {
        int empIdSequence = 1;
        // Employee emp = new Employee();
        EmployeeService empService = new EmployeeService();
        Scanner sc = new Scanner(System.in);
        int n = 0;
        do {
            System.out.println();
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. View All Employees");
            System.out.println("6. Print Statistics");
            System.out.println("7. Import");
            System.out.println("8. Export");
            System.out.println("9. Exit");
            System.out.println("Enter the option: ");
            int number = sc.nextInt();
            switch (number) {
                case 1:
                    Employee emp = new Employee();

                    emp.setEmpId(empIdSequence++);

                    System.out.println("Enter employee Name:  ");
                    emp.setName(sc.next());

                    System.out.println("Enter employee Age: ");
                    emp.setAge(sc.next());

                    System.out.println("Enter employee Designation: ");
                    emp.setDesignation(sc.next());

                    System.out.println("Enter employee Department: ");
                    emp.setDepartment(sc.next());

                    System.out.println("Enter employee Country: ");
                    emp.setCountry(sc.next());

                    empService.addEmployee(emp);  // add to the Employee aray
                    System.out.println("Employee has been added Succesfully ");
                    break;

                case 2:
                    int empId = readEmployeeId();
                    try {
                        empService.viewEmployee(empId);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    int empIdtoUpdate = readEmployeeId();
                    Employee empWithID = null;
                    try {
                        empWithID = empService.findById(empIdtoUpdate);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Enter the employee details to update: ");

                    empWithID.setEmpId(empIdtoUpdate);
                    System.out.println("Enter employee Name:  ");
                    empWithID.setName(sc.next());

                    System.out.println("Enter employee Age: ");
                    empWithID.setAge(sc.next());

                    System.out.println("Enter employee Designation: ");
                    empWithID.setDesignation(sc.next());

                    System.out.println("Enter employee Department: ");
                    empWithID.setDepartment(sc.next());

                    System.out.println("Enter employee Country: ");
                    empWithID.setCountry(sc.next());

                    System.out.println("Employee has been updated Succesfully ");
                    break;
                case 4:
                    String status = empService.deleteEmployee(readEmployeeId());
                    System.out.println(status);
                    break;
                case 5:
                    empService.viewallEmployees();
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    return;
            }
            n++;
        } while (n < 20);
    }

    static int readEmployeeId() {
        do {
            try {
                System.out.println("Enter empID ");
                Scanner sc = new Scanner(System.in);
                int empId = sc.nextInt();
                return empId;
            } catch (InputMismatchException e) {
                System.out.println("Sorry you have entered invalid Employee ID");
            }
        } while (true);
    }
}


