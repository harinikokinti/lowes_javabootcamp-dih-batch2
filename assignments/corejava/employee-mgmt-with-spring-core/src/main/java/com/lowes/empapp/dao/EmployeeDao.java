package com.lowes.empapp.dao;


import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;

import java.util.List;

public interface EmployeeDao {
    public boolean create(Employee emp);

    public boolean update(Employee emp);

    public boolean delete(int empId);

    public Employee get(int empId) throws EmployeeNotFoundException;

    public List<Employee> getAll();

}