package com.lowes.empapp;

import com.lowes.empapp.config.EmployeeConfig;
import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import com.lowes.empapp.service.EmployeeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EmployeeManagementMain {

 //   public static EmployeeServiceImpl empService;
    public static Employee emp;

   private static EmployeeService employeeService;

    public static void main(String[] args) {

        AbstractApplicationContext context  = new AnnotationConfigApplicationContext(EmployeeConfig.class);
        employeeService =  context.getBean("employeeService", EmployeeService.class);


        ExecutorService exs = Executors.newFixedThreadPool(5);
      //  empService = new EmployeeServiceImpl();
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
                    employeeService.create(readEmployee());
                    System.out.println("Employee has been added Succesfully ");
                    break;
                case 2: // View Employee
                    int empId = readEmployeeId();
                    try {
                        emp = employeeService.get(empId);
                        printHeader();
                        printEmployee(emp);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3: // Update employee
                    int empIdToUpdate = readEmployeeId();
                    Employee empNew = readEmployee();
                    empNew.setEmpId(empIdToUpdate);
                    try {
                        employeeService.update(empNew);
                        System.out.println("Employee has been updated Succesfully ");
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4: // Delete Employee
                    int empIDToDelete = readEmployeeId();
                    try {
                        employeeService.delete(empIDToDelete);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5: // View all Employees
                    List<Employee> employees = employeeService.getAll();
                    printEmployees(employees);
                    break;
                case 6: // Print Statistics
                    printStatistics();
                    break;
                case 7: // Import
                    Future<Boolean> importFuture = exs.submit(() -> {
                        Thread.sleep(2000);
                        System.out.println("Import Process on Thread name: " + Thread.currentThread().getName());
                        employeeService.bulkImport();
                        return true;
                    });
                    break;
                case 8: // Export
                    Future<Boolean> exportFuture = exs.submit(() -> {
                        Thread.sleep(2000);
                        System.out.println("Export Process on Thread name: " + Thread.currentThread().getName());
                        employeeService.bulkExport();
                        return true;
                    });
                    break;
                case 9:
                    exs.shutdown();
                    context.registerShutdownHook();
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

        // validate age
        boolean valAgeStatus = true;
        do {
            try {
                System.out.println("Enter employee Age: ");
                String message = "Invalid age: Enter age > 20 and < 60 : ";
                emp.setAge(sc.nextInt());
                valAgeStatus = employeeService.validate(emp, message, (employee) -> employee.getAge() >= 20 && employee.getAge() <= 60);
            } catch (InputMismatchException e) {
                System.out.println("Sorry you have entered invalid Age ");
                sc.next();  // if com.lowes.empapp.exception comes, we should move to the next token
                // https://stackoverflow.com/questions/6374623/java-util-scanner-why-my-nextdouble-does-not-prompt
                valAgeStatus = false;
            }
        } while (!valAgeStatus);

        System.out.println("Enter employee Designation: ");
        emp.setDesignation(sc.next());
        System.out.println("Enter employee Department: ");
        emp.setDepartment(sc.next());
        System.out.println("Enter employee Country: ");
        emp.setCountry(sc.next());

        // validate Salary
        boolean valSalStatus = true;
        do {
            try {
                System.out.println("Enter employee Salary: ");
                String message = "Invalid salary: Enter salary > 0 ";
                emp.setSalary(sc.nextDouble());
                valSalStatus = employeeService.validate(emp, message, (employee) -> employee.getSalary() > 0);
            } catch (InputMismatchException e) {
                System.out.println("Sorry you have entered invalid Salary ");
                sc.next();  // if com.lowes.empapp.exception comes, we should move to the next token
                // https://stackoverflow.com/questions/6374623/java-util-scanner-why-my-nextdouble-does-not-prompt
                valSalStatus = false;
            }
        } while (!valSalStatus);


        // validate Date of Joining
        boolean valDojStatus = true;
        do {
            try {
                System.out.println("Enter Date of joining (doj) in dd-mm-yyyy  format: ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");  // doubt
                LocalDate doj = LocalDate.parse(sc.next(), formatter);
                emp.setDoj(doj);
                valDojStatus = true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date format, Enter date in dd-mm-yyyy  format");
                valDojStatus = false;
            }
        } while (!valDojStatus);
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

    static void printStatistics() {
        System.out.println(" Number of Employees older than forty years:  " +
                employeeService.getEmployeeCountAgeGreaterThan(e -> e.getAge() > 40));
        System.out.println("List of Employee Ids older than 40 years: " +
                employeeService.getEmployeeIdsAgeGreaterThan(40));
        System.out.println("Employee count by Department: " +
                employeeService.getEmployeeCountByDepartment());
        System.out.println("Employee count by Department sorted: " +
                employeeService.getEmployeeCountByDepartmentOrdered());
        System.out.println("Average Employee age by Department: " +
                employeeService.getAvgEmployeeAgeByDept());
        System.out.println("List Departments have more than 3 employees: " +
                employeeService.getDepartmentsHaveEmployeesMoreThan(3));
        System.out.println("List Employees starts with " + "'S':" +
                employeeService.getEmployeeNamesStartsWith("S"));
        System.out.println("Average Employee Service by Department: " +
                employeeService.getAvgEmployeeServiceByDept());
    }



}

