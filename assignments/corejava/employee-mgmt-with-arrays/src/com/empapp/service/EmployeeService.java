package com.empapp.service;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;

public class EmployeeService {
    private Employee[] employees = new Employee[10];
    private int index;

    public void addEmployee(Employee emp) {
        employees[index++] = emp;
    }

    public void viewEmployee(int empId) {
        Employee employee = findById(empId);
        printEmployee(employee);
    }

    public Employee findById(int empId) {
        Employee emp = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getEmpId() == empId) {
                    emp = employees[i];
                }
            }
        }
        if (emp == null) {
            throw new EmployeeNotFoundException("Sorry Employee was not found ");
        }
        return emp;
    }

    public void viewallEmployees() {
        System.out.println("  EmpId     Name     Age        Designation         Department      Country ");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                printEmployee(employees[i]);
            }
        }
    }

    public String deleteEmployee(int empId) {
        String status = null;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getEmpId() == empId) {
                    employees[i] = null;
                    status = "Deleted Succesfully";
                }
            }
        }
        return status;
    }


    private void printEmployee(Employee employee) {
        System.out.println("    " + employee.getEmpId() + "       " + employee.getName() + "        " + employee.getAge() + "           " +
                employee.getDesignation() + "           " + employee.getDepartment() + "                " +
                employee.getCountry());
    }

}