package com.ushwamala.springboot.dao;

import com.ushwamala.springboot.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(int employeeId);

    void saveEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    Employee updateEmployee(int employeeId, Employee newEmployee);
}
