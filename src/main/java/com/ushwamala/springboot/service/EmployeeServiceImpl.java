package com.ushwamala.springboot.service;

import com.ushwamala.springboot.Repository.EmployeeRepository;
import com.ushwamala.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
       employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Optional<Employee> tempEmployee = employeeRepository.findById(employeeId);
        Employee employee = null;
        try{
            if(tempEmployee.isPresent()){
                employee = tempEmployee.get();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void deleteEmployee(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void updateEmployee(int employeeId,Employee newEmployee) {
        Employee currentEmployee = getEmployeeById(employeeId);

        //Swap the the old data with the new data
        currentEmployee.setFirstName(newEmployee.getFirstName());
        currentEmployee.setLastName(newEmployee.getLastName());
        currentEmployee.setEmail(newEmployee.getEmail());

        employeeRepository.save(currentEmployee);


    }
}
