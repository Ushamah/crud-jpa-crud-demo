package com.ushwamala.springboot.controller;

import com.ushwamala.springboot.entity.Employee;
import com.ushwamala.springboot.exceptionhandling.EmployeeNotFoundException;
import com.ushwamala.springboot.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    //https://www.youtube.com/watch?v=8s9I1G4tXhA&ab_channel=JavaBrains for the api documentation

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @ApiOperation(value = "Used for retrieving all the employees in the database")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("employees/{employeeId}")
    @ApiOperation(value = "Finds employee by employeeId",
            notes = "Provide an employeeId to look up specific employee from the employee directory",
            response = Employee.class)
    public Employee getEmployeeById(@ApiParam(value = "ID value for the employee to be retrieved", readOnly = true)
                                    @PathVariable int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with id = " + employeeId + " not found");
        }
        return employee;
    }

    @PostMapping("/employees")
    @ApiOperation(value = "Used for creating a new employee which is then saved in the database")
    public Employee saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employeeService.getEmployeeById(employee.getId());
    }

    @PutMapping("employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
        if ((employeeService.getEmployeeById(employeeId)) == null) {
            throw new EmployeeNotFoundException("Employee with id = " + employeeId + " not found");
        }
        employeeService.updateEmployee(employeeId, employee);
        return employeeService.getEmployeeById(employee.getId());
    }

    @DeleteMapping("employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId) {
        if ((employeeService.getEmployeeById(employeeId)) == null) {
            throw new EmployeeNotFoundException("Employee with id = " + employeeId + " not found");
        } else {
            employeeService.deleteEmployee(employeeId);
        }
    }
}
