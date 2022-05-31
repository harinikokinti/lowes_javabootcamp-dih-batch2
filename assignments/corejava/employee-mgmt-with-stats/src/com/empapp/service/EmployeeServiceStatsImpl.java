package com.empapp.service;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceStatsImpl implements EmployeeService {
    private Map<Integer, Employee> employees = new HashMap<>();
    int empIdSequence = 1;

    Comparator<Employee> sortByNameComparator = (o1, o2) -> o1.getName().compareTo(o2.getName()); // to sort by Employee name

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
        Employee empToUpdate = this.findById(empNew.getEmpId()); // throws EmployeeNotFoundException if emp null
        empNew.setModifiedTime(LocalDateTime.now());
        empNew.setCreatedTime(empToUpdate.getCreatedTime());
        employees.put(empToUpdate.getEmpId(), empNew);
        return true;
    }

    @Override
    public boolean delete(int empId) {
        Employee deletedEmp = employees.remove(empId); // returns value associated with the key after deleting
        // if no value associated with the key, it returns null
        if (deletedEmp != null) {
            return true;
        } else {
            throw new EmployeeNotFoundException("Sorry Employee was not found ");
        }
    }

    @Override
    public List<Employee> getAll() {  // View all Employees
        List<Employee> employeeList = new ArrayList(employees.values());
        Collections.sort(employeeList, sortByNameComparator);  // sorts the list by emp name
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

    @Override
    public void bulkImport() {
        System.out.println("Import Started ");
        BufferedReader in = null;
        String line = null;
        try {
            in = new BufferedReader(new FileReader("/Users/dhilli/codebase/lowes_javabootcamp-dih-batch2/assignments/corejava/employee-mgmt-with-stats/src/com/empapp/input.txt"));
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
        emp.setEmpId(Integer.parseInt(tokens[0]));
        emp.setName(tokens[1]);
        emp.setAge(Integer.parseInt(tokens[2]));
        emp.setDesignation(tokens[3]);
        emp.setDepartment(tokens[4]);
        emp.setCountry(tokens[5]);
        emp.setSalary(Double.parseDouble(tokens[6]));
        emp.setDoj(LocalDate.parse(tokens[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        emp.setCreatedTime(LocalDateTime.parse(tokens[8]));
        return emp;
    }

    @Override
    public void bulkExport() {
        System.out.println("Export Started ");
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("src/com/empapp/output.txt"));
            //   out.write("hi welcome");
            for (Employee emp : employees.values()) {
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

    @Override
    public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition) {
        return employees.values().stream().filter(condition).count();
    }

    @Override
    public List<Integer> getEmployeeIdsAgeGreaterThan(int age) {
        return employees.values()
                .stream()
                .filter(employee -> employee.getAge() > age)
                .map(p -> p.getEmpId()).collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getEmployeeCountByDepartment() {
        return employees.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    @Override
    public Map<String, Long> getEmployeeCountByDepartmentOrdered() {
        return employees.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting()));
    }

    @Override
    public Map<String, Double> getAvgEmployeeAgeByDept() {
        return employees.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getAge)))
                .entrySet()
                .stream()
                .map(entry -> {
                    entry.setValue(roundDouble(entry.getValue())); // round double value to 2 decimal points
                    return entry;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));  // convert entry to map
    }

    @Override
    public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria) {
        return employees.values()
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
        return employees.values()
                .stream()
                .map(Employee::getName)
                .filter(name -> name.startsWith(prefix))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Double> getAvgEmployeeServiceByDept() {

        return employees.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(employees -> {
                    Period period = Period.between(employees.getDoj(), LocalDate.now());
                    return period.getYears();
                })))
                .entrySet()
                .stream()
                .map(entry -> {
                    entry.setValue(roundDouble(entry.getValue())); // round double value to 2 decimal points
                    return entry;
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));  // convert entry to map
    }

    private double roundDouble(Double value) {
        BigDecimal val = BigDecimal.valueOf(value);  // convert double value to BigDecimal
        val = val.setScale(2, RoundingMode.HALF_UP);  // Set scale to 2 decimal points and round to nearest big value
        return val.doubleValue(); // convert BigDecimal to double                                        // Ex : 4.789 = 4.79
    }
}