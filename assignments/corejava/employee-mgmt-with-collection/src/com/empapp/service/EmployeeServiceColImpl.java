package com.empapp.service;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceColImpl implements EmployeeService {
    private Map<Integer, Employee> employees = new HashMap<>();
    int empIdSequence = 1;

    @Override
    public boolean create(Employee emp) {
        emp.setEmpId(empIdSequence++);
        emp.setCreatedTime(LocalDateTime.now());
        employees.put(emp.getEmpId(), emp);
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
        empNew.setModifiedTime(LocalDateTime.now());
        empNew.setCreatedTime(empToUpdate.getCreatedTime());
        employees.put(empToUpdate.getEmpId(), empNew);
        return true;
    }

    @Override
    public boolean delete(int empId) {
        Employee deletedEmp = employees.remove(empId); // returns value associated with the key after deleting
        // if no value associated with the key, returns null
        if (deletedEmp != null) {
            return true;
        } else {
            throw new EmployeeNotFoundException("Sorry Employee was not found ");
        }
    }

    @Override
    public List<Employee> getAll() {  // View all Employees
        List<Employee> employeeList = new ArrayList(employees.values());
        return employeeList;
    }

    public Employee findById(int empId) {
        Employee emp = employees.get(empId);
        if (emp == null) {
            throw new EmployeeNotFoundException("Sorry Employee was not found ");
        } else {
            return emp;
        }
    }
}