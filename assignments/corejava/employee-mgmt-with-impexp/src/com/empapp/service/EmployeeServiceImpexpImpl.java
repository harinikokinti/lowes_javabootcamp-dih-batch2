package com.empapp.service;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeServiceImpexpImpl implements EmployeeService {
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
        Collections.sort(employeeList, sortByNameComparator);
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
            in = new BufferedReader(new FileReader("/Users/dhilli/codebase/lowes_javabootcamp-dih-batch2/assignments/corejava/employee-mgmt-with-impexp/src/com/empapp/input.txt"));
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
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
                create(emp);
            }
            System.out.println("Bulk Import Success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
