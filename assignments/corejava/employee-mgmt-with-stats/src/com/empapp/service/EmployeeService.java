package com.empapp.service;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface EmployeeService {
    public boolean create(Employee emp);

    public boolean update(Employee emp);

    public boolean delete(int empId);

    public Employee get(int empId) throws EmployeeNotFoundException;

    public List<Employee> getAll();

    public void bulkImport();

    public void bulkExport();

    public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition);

    public List<Integer> getEmployeeIdsAgeGreaterThan(int age);

    public Map<String, Long> getEmployeeCountByDepartment();

    public Map<String, Long> getEmployeeCountByDepartmentOrdered();

    public Map<String, Double> getAvgEmployeeAgeByDept();

    public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria);

    public List<String> getEmployeeNamesStartsWith(String prefix);

    public Map<String, Double> getAvgEmployeeServiceByDept();

}