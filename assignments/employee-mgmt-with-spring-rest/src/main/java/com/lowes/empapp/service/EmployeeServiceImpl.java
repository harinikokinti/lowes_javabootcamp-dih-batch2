package com.lowes.empapp.service;

import com.lowes.empapp.dao.EmployeeDao;
import com.lowes.empapp.dao.EmployeeDaoImpl;
import com.lowes.empapp.exception.InputValidationException;
import com.lowes.empapp.model.Employee;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Autowired
    EmployeeDao employeeDaoJdbc;
    List<Employee> employees = null;

    @Override
    public Employee create(Employee emp) {
        return employeeDaoJdbc.create(validateEmpInput(emp));
    }

    @Override
    public Employee get(int empId) {
        return employeeDaoJdbc.get(empId);
    }

    @Override
    public Employee update(Employee empNew) {
        return employeeDaoJdbc.update(validateEmpInput(empNew));
    }

    @Override
    public boolean delete(int empId) {
        return employeeDaoJdbc.delete(empId);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDaoJdbc.getAll();
    }

    // Validation Logic

    public Employee validateEmpInput(Employee emp) {
        List<String> errors = new ArrayList<>();
        if (StringUtils.isNullOrEmpty(emp.getName())) {
            errors.add("Name cannot be empty");
        }

        if (emp.getAge() <= 0 || StringUtils.isNullOrEmpty(String.valueOf(emp.getAge()))) {
            errors.add("Age cannot be 0 or empty");
        }

        // validate age
        if (!validate(emp, (employee) -> employee.getAge() >= 20 && employee.getAge() <= 60)) {
            errors.add("Invalid age: Enter age > 20 and < 60 : ");
        }

        if (StringUtils.isNullOrEmpty(emp.getDesignation())) {
            errors.add("Designation cannot be empty");
        }
        if (StringUtils.isNullOrEmpty(emp.getDepartment())) {
            errors.add("Department cannot be empty");
        }
        if (StringUtils.isNullOrEmpty(emp.getCountry())) {
            errors.add("Country cannot be empty");
        }

        // validate salary
        if (!validate(emp, (employee) -> employee.getSalary() > 0.0)) {
            errors.add("Invalid salary: Enter salary > 0");
        }

        if (!errors.isEmpty()) {
            throw new InputValidationException(errors);
        }
        return emp;
    }


    // Validation Method
    public boolean validate(Employee emp, Predicate<Employee> condition) {
        return condition.test(emp);
    }


}
