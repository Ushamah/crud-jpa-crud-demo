package com.ushwamala.springboot.dao;

import com.ushwamala.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;  //native hibernate implementation using only the entityManager


    @Override
    public List<Employee> getAllEmployees() {

        //create a query
        Query query = entityManager.createQuery("from Employee");

        //create query for the employees list
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee tempEmployee = entityManager.find(Employee.class, employeeId);
        return tempEmployee;
    }

    @Override
    public void saveEmployee(Employee employee) {
        //save the employee
        entityManager.merge(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {

        //create a query
        Query query = entityManager.createQuery("delete from Employee where id=:employeeId");

        query.setParameter("employeeId", employeeId);

        query.executeUpdate();
    }

    @Override
    public Employee updateEmployee(int employeeId, Employee newEmployee) {

        //get the current hibernate session
        Employee currentEmployee = getEmployeeById(employeeId);

        //Swap the the old data with the new data
        currentEmployee.setFirstName(newEmployee.getFirstName());
        currentEmployee.setLastName(newEmployee.getLastName());
        currentEmployee.setEmail(newEmployee.getEmail());

        entityManager.merge(currentEmployee);

        return currentEmployee;
    }


}
