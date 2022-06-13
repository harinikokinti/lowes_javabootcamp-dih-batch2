package com.lowes.empapp.service;

import com.lowes.empapp.exception.EmployeeNotFoundException;
import com.lowes.empapp.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

 public interface EmployeeService {
    boolean create(Employee emp);

    boolean update(Employee emp);

     boolean delete(int empId);

     Employee get(int empId) throws EmployeeNotFoundException;

     List<Employee> getAll();

     void bulkImport();

     void bulkExport();

     boolean validate(Employee emp, String message, Predicate<Employee> condition);

     long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition);

     List<Integer> getEmployeeIdsAgeGreaterThan(int age);

     Map<String, Long> getEmployeeCountByDepartment();

     Map<String, Long> getEmployeeCountByDepartmentOrdered();

     Map<String, Double> getAvgEmployeeAgeByDept();

     List<String> getDepartmentsHaveEmployeesMoreThan(int criteria);

     List<String> getEmployeeNamesStartsWith(String prefix);

     Map<String, Double> getAvgEmployeeServiceByDept();

}