package com.empapp;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;
import com.empapp.service.EmployeeServiceColImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementMain {
    public static void main(String[] args) {
        EmployeeServiceColImpl empService = new EmployeeServiceColImpl();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("---------------Employee Management application-------------");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. View All Employees");
            System.out.println("6. Exit");
            System.out.println("Enter the option: ");
            int number = sc.nextInt();
            switch (number) {
                case 1: // Create employee
                    empService.create(readEmployee());
                    System.out.println("Employee has been added Succesfully ");
                    break;
                case 2: // View Employee
                    int empId = readEmployeeId();
                    try {
                        Employee emp = empService.get(empId);
                        printHeader();
                        printEmployee(emp);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3: // Update employee
                    int empIdToUpdate = readEmployeeId();
                    try {
                        empService.findById(empIdToUpdate);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    Employee empNew = readEmployee();
                    empNew.setEmpId(empIdToUpdate);
                    empService.update(empNew);
                    System.out.println("Employee has been updated Succesfully ");
                    break;
                case 4: // Delete Employee
                    int empIDToDelete = readEmployeeId();
                    try {
                        boolean status = empService.delete(empIDToDelete);
                        if (status) {
                            System.out.println("Employee Deleted Successfully");
                        }
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5: // View all Employees
                    List<Employee> employees = empService.getAll();
                    printEmployees(employees);
                    break;
                case 6:
                    return;
            }
        } while (true);
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

    static Employee readEmployee() {
        Scanner sc = new Scanner(System.in);
        Employee emp = new Employee();
        System.out.println("Enter the employee details: ");
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
        return emp;
    }

    static void printEmployee(Employee employee) {
        System.out.println("    " + employee.getEmpId() + "       " + employee.getName() + "      " + employee.getAge() + "        " +
                employee.getDesignation() + "           " + employee.getDepartment() + "             " +
                employee.getCountry());
    }

    static void printEmployees(List<Employee> employees) {
        printHeader();
        for (Employee employee : employees) {
            printEmployee(employee);
        }
    }

    static void printHeader() {
        System.out.println("  EmpId     Name     Age        Designation         Department      Country ");
    }
}


