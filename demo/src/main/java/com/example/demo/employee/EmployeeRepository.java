package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository is an interface that provides access to data in a database
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}