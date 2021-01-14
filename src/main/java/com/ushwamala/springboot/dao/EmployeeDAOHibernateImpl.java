package com.ushwamala.springboot.dao;

import com.ushwamala.springboot.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    @Autowired
    private EntityManager entityManager;  //native hibernate implementation using entityManager and session


    @Override
    public List<Employee> getAllEmployees() {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //create query for the employees list
        Query<Employee> employeeQuery = currentSession.createQuery("from Employee", Employee.class);

        //execute query and get result list
        List<Employee> employees = employeeQuery.getResultList();

        return employees;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {

        List<Employee> employees = getAllEmployees();
        Employee tempEmployee = null;
        try {
            for (Employee employee : employees) {
                if (employee.getId() == employeeId) {
                    tempEmployee = employee;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EMPLOYEE DOESN'T EXIST IN DATABASE");

        }
        return tempEmployee;
    }

    @Override
    public void saveEmployee(Employee employee) {
        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.save(employee);

    }

    @Override
    public void deleteEmployee(int employeeId) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        //retrieve the employee with the given employeeId
        Employee employee = getEmployeeById(employeeId);


        if(employee != null){
            //delete the employee
            currentSession.delete(employee);
            System.out.println("Employee with id = " + employeeId + " has successfully been deleted");
        }

    }

    @Override
    public Employee updateEmployee(int employeeId, Employee newEmployee) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        Employee currentEmployee = getEmployeeById(employeeId);

        //Swap the the old data with the new data
        currentEmployee.setFirstName(newEmployee.getFirstName());
        currentEmployee.setLastName(newEmployee.getLastName());
        currentEmployee.setEmail(newEmployee.getEmail());

        currentSession.save(currentEmployee);

        return currentEmployee;
    }


}
