package com.lowes.empapp.dao;


import com.lowes.empapp.model.Employee;

import java.util.List;

public interface EmployeeDao {
    public Employee create(Employee emp);

    public Employee update(Employee emp);

    public boolean delete(int empId);

    public Employee get(int empId) ;

    public List<Employee> getAll();

}
