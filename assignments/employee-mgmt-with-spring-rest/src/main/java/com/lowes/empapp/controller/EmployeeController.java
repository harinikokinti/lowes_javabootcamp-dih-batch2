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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    public EmployeeServiceImpl employeeService;

    // Create Employee
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ResponseMessage> create(@RequestBody Employee employee) throws URISyntaxException {
        Employee empCreated = employeeService.create(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        ResponseMessage response = new ResponseMessage("Success", "Employee created successfully");
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
    public ResponseEntity<ResponseMessage> update(@PathVariable int empId, @RequestBody Employee employee) {
        employee.setEmpId(empId);
        Employee empUpdated = employeeService.update(employee);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
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