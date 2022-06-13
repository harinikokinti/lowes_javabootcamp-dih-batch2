package com.empapp;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;
import com.empapp.service.EmployeeServiceImpexpImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class EmployeeManagementMain {
    public static void main(String[] args) {
        ExecutorService exs = Executors.newFixedThreadPool(5);
        EmployeeServiceImpexpImpl empService = new EmployeeServiceImpexpImpl();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("---------------Employee Management application-------------");
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
                case 6: // Print Statistics
                    return;
                case 7: // Import
                    Future<Boolean> importFuture = exs.submit(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            Thread.sleep(2000);
                            System.out.println("Import Process on Thread name: " + Thread.currentThread().getName());
                            empService.bulkImport();
                            return true;
                        }
                    });
                    break;
                case 8: // Export
                    Future<Boolean> exportFuture = exs.submit(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            Thread.sleep(2000);
                            System.out.println("Export Process on Thread name: " + Thread.currentThread().getName());
                            empService.bulkExport();
                            return true;
                        }
                    });
                    break;
                case 9:
                    exs.shutdown();
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
        emp.setAge(sc.nextInt());
        System.out.println("Enter employee Designation: ");
        emp.setDesignation(sc.next());
        System.out.println("Enter employee Department: ");
        emp.setDepartment(sc.next());
        System.out.println("Enter employee Country: ");
        emp.setCountry(sc.next());
        System.out.println("Enter employee Salary: ");
        emp.setSalary(sc.nextDouble());
        System.out.println("Enter Date of joining (doj) in dd-mm-yyyy  format: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");  // doubt
        emp.setDoj((LocalDate.parse(sc.next(), formatter)));
        return emp;
    }

    static void printEmployee(Employee employee) {
        System.out.println(employee.getEmpId() + "\t" + employee.getName() + "\t\t" + employee.getAge() + "\t\t\t" +
                employee.getDesignation() + "\t\t" + employee.getDepartment() + "\t\t" +
                employee.getCountry() + "\t\t" + employee.getSalary() + "\t\t\t" + employee.getDoj() + "\t\t" +
                employee.getCreatedTime() + "\t\t\t" + employee.getModifiedTime());
    }

    static void printEmployees(List<Employee> employees) {
        printHeader();
        for (Employee employee : employees) {
            printEmployee(employee);
        }
    }

    static void printHeader() {
        System.out.println("EmpId \t Name \t Age \t Designation \t Department \t Country \t Salary \t Doj \t\t CreatedTime \t\t\t ModifiedTime");
    }

}


