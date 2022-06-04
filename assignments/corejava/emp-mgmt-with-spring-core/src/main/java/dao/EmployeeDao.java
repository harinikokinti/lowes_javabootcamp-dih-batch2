package dao;


import exception.EmployeeNotFoundException;
import model.Employee;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface EmployeeDao {
    public boolean create(Employee emp);

    public boolean update(Employee emp);

    public boolean delete(int empId);

    public Employee get(int empId) throws EmployeeNotFoundException;

    public List<Employee> getAll();

}