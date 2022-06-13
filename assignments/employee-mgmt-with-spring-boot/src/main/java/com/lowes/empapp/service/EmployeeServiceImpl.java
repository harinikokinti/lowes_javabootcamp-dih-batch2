package com.lowes.empapp.service;

import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.repository.EmployeeRespository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

@Service
public class EmployeeServiceImpl implements  EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRespository repo;
    
    List<Employee> employees = null;

    @Override
    public Employee create(Employee emp) {       
        return repo.save(emp);
    }

    @Override
    public Employee get(int empId) {
    	try {
            return repo.findById(empId).get();
    		
    	} catch(NoSuchElementException e) {
    		throw new EmployeeException("Sorry Employee not found in the database",e);
    	}
    	
    }

    @Override
    public Employee update(int empId,Employee empNew) {
    	try {
    		Employee employeeById = repo.findById(empId).get();
            empNew.setCreatedTime(employeeById.getCreatedTime());  
        	return repo.save(empNew);  //doubt, how to avoid full update
    	}catch(NoSuchElementException e) {
    		throw new EmployeeException("Sorry Employee not found in the database",e);
    	}        
    }

    @Override
    public void delete(int empId) {
    	try {
       repo.deleteById(empId);
    	}catch(EmptyResultDataAccessException e) {
    		throw new EmployeeException("Sorry Employee not found in the database",e);
    	}   
    }

    @Override
    public List<Employee> getAll() {  
    	List<Employee> empList = repo.findAll();
    	if (empList.isEmpty()) {
            throw new EmployeeException("Sorry no employees found in the database");
        }
    	return empList;
    }
    
    

    // Validation Method
    public boolean validate(Employee emp, Predicate<Employee> condition) {
        return condition.test(emp);
    }


}
