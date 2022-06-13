package com.lowes.empapp.service;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface EmployeeService {
    public Employee create(Employee emp);

    public Employee update(int id,Employee emp);

    public void delete(int empId);

    public Employee get(int empId) throws EmployeeException;

    public List<Employee> getAll();

}