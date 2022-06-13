package com.lowes.empapp.controller;

import com.lowes.empapp.exception.InputValidationException;
import com.lowes.empapp.model.Employee;
import com.lowes.empapp.model.ResponseMessage;
import com.lowes.empapp.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    
    @Autowired
    public EmployeeServiceImpl employeeService;

    // Create Employee
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ResponseMessage> create(@Valid @RequestBody Employee employee) throws URISyntaxException {
    	
    	//logger.info("Entered into create controller", employee.getCreatedTime());
    	
    	System.out.println("Entered into create controller"+ employee.getCreatedTime());
        Employee empCreated = employeeService.create(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empCreated.getEmpId()).toUri();
        ResponseMessage response = new ResponseMessage("Success", "Employee created successfully");
        System.out.println("Entered into create controller after creation"+ employee.getCreatedTime());
        return ResponseEntity.created(location).body(response);
    }

    // Get all employees
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Employee> getAll() {  // date format coming as string with ,
        return employeeService.getAll();
    }

    // Get employee
    @GetMapping(path = "/{empId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Employee get(@PathVariable int empId) {
        return employeeService.get(empId);
    }

    // Update employee
    @PutMapping(path = "/{empId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ResponseMessage> update(@PathVariable int empId, @Valid @RequestBody Employee employee) {
        employee.setEmpId(empId);
        Employee empUpdated = employeeService.update(empId,employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(empUpdated.getEmpId()).toUri();
        ResponseMessage response = new ResponseMessage("Success", "Employee updated successfully");
        return ResponseEntity.created(location).body(response);
    }

    // Delete employee
    @DeleteMapping(path = "/{empId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ResponseMessage> delete(@PathVariable int empId) {
        employeeService.delete(empId);
        ResponseMessage response = new ResponseMessage("Success", "Employee deleted successfully");
        return ResponseEntity.ok().body(response);
    }

}