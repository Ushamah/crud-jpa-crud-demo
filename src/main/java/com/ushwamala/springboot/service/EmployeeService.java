package com.ushwamala.springboot.service;

import com.ushwamala.springboot.entity.Employee;


import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(int employeeId);

    void deleteEmployee(int employeeId);

    void updateEmployee(int employeeId, Employee employee);
}
