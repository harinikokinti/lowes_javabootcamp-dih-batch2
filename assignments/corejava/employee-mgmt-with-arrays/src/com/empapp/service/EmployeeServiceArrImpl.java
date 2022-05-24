package com.empapp.service;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;

public class EmployeeServiceArrImpl implements EmployeeService {
    private Employee[] employees = new Employee[4];
    private int index;
    int empIdSequence = 1;

    @Override
    public boolean create(Employee emp) {
        emp.setEmpId(empIdSequence++);
        employees[index++] = emp;
        return true;
    }

    @Override
    public Employee get(int empId) {
        Employee employee = findById(empId);
        return employee;
    }

    @Override
    public boolean update(Employee empNew) {
        Employee empToUpdate = this.findById(empNew.getEmpId());
        empToUpdate.setName(empNew.getName());
        empToUpdate.setAge(empNew.getAge());
        empToUpdate.setDesignation(empNew.getDesignation());
        empToUpdate.setDepartment(empNew.getDepartment());
        empToUpdate.setCountry(empNew.getCountry());
        return true;
    }

    @Override
    public boolean delete(int empId) {
        boolean status = false;
        int deletedIndex = 0;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employees[i].getEmpId() == empId) {
                    employees[i] = null;
                    deletedIndex = i;
                    index--;
                    status = true;
                    break;
                }
            }
        }

        if (status) {  // shifting the elements upwards
            for (int i = deletedIndex; i < employees.length; i++) {
                if ((i + 1) >= employees.length) {  // when we get last element, make it null
                    employees[i] = null;
                    break;
                } else {
                    employees[i] = employees[i + 1];  // shift the element to upwaards
                }
            }
        } else {
            throw new EmployeeNotFoundException("Employee was not found ");
        }
        return status;
    }

    @Override
    public Employee[] getAll() {   // View all Employees
        return employees;
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
}