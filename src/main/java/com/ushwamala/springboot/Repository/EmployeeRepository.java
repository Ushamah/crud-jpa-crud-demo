package com.ushwamala.springboot.Repository;

import com.ushwamala.springboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Integer> {

}
