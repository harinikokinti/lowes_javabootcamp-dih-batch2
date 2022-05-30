package com.empapp.service;

import com.empapp.dao.EmployeeDao;
import com.empapp.dao.EmployeeDaoJdbcImpl;
import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceJdbcImpl implements EmployeeService {
    EmployeeDaoJdbcImpl employeeDaoJdbc = new EmployeeDaoJdbcImpl();
    List<Employee> employees = null;

    @Override
    public boolean create(Employee emp) {
        employeeDaoJdbc.create(emp);
        return true;
    }

    @Override
    public Employee get(int empId) {
        return employeeDaoJdbc.get(empId);
    }

    @Override
    public boolean update(Employee empNew) {
        employeeDaoJdbc.update(empNew);
        return true;
    }

    @Override
    public boolean delete(int empId) {
        return employeeDaoJdbc.delete(empId);
    }

    @Override
    public List<Employee> getAll() {  // View all Employees
        return employeeDaoJdbc.getAll();
    }

    @Override
    public void bulkImport() {
        System.out.println("Import Started ");
        BufferedReader in = null;
        String line = null;
        try {
            in = new BufferedReader(new FileReader("/Users/dhilli/codebase/lowes_javabootcamp-dih-batch2/assignments/corejava/employee-mgmt-with-jdbc/src/com/empapp/input.txt"));
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                Employee emp = buildEmployee(tokens);
                create(emp);
            }
            System.out.println("Bulk Import Success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Employee buildEmployee(String[] tokens) {
        Employee emp = new Employee();
        emp.setName(tokens[0]);
        emp.setAge(Integer.parseInt(tokens[1]));
        emp.setDesignation(tokens[2]);
        emp.setDepartment(tokens[3]);
        emp.setCountry(tokens[4]);
        emp.setSalary(Double.parseDouble(tokens[5]));
        emp.setDoj(LocalDate.parse(tokens[6], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        emp.setCreatedTime(LocalDateTime.parse(tokens[7]));
        return emp;
    }

    @Override
    public void bulkExport() {
        System.out.println("Export Started ");
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("src/com/empapp/output.txt"));
            //   out.write("hi welcome");
            for (Employee emp : getAll()) {
                out.write(emp.getEmpId() + "," + emp.getName() + "," + emp.getAge() + "," +
                        emp.getDepartment() + "," + emp.getDesignation() + "," + emp.getCountry() + "," + emp.getSalary() + "," +
                        emp.getDoj() + "," + emp.getCreatedTime() + "," + emp.getModifiedTime());
                out.write("\n");
            }
            System.out.println("Bulk Export Success");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    // Validation Method

    public boolean validate(Employee emp, String message, Predicate<Employee> condition) {
        boolean predResult = condition.test(emp);
        if (!predResult) {
            System.out.println(message);
        }
        return predResult;
    }

    // Print Statistics Methods

    public List<Employee> empCollection() {
        if (employees == null) {
            employees = getAll();
        }
        return employees;
    }

    @Override
    public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition) {
        employees = empCollection();
        return getAll().stream().filter(condition).count();
        //    return employees.values().stream().filter(condition).count();
    }

    @Override
    public List<Integer> getEmployeeIdsAgeGreaterThan(int age) {
        return employees
                .stream()
                .filter(employee -> employee.getAge() > age)
                .map(p -> p.getEmpId()).collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getEmployeeCountByDepartment() {
        return employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    @Override
    public Map<String, Long> getEmployeeCountByDepartmentOrdered() {
        return employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));
    }

    @Override
    public Map<String, Double> getAvgEmployeeAgeByDept() {
        return employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getAge)));
    }

    @Override
    public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria) {
        return employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > criteria)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEmployeeNamesStartsWith(String prefix) {
        return employees
                .stream()
                .map(Employee::getName)
                .filter(name -> name.startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Double> getAvgEmployeeServiceByDept() {
        return employees
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(employees -> {
                    Period period = Period.between(employees.getDoj(), LocalDate.now());
                    return period.getYears();
                })));
    }
}