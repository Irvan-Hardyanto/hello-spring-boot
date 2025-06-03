package com.example.demo.employee;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
/**
 * Service layer is where all the business logic lies
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepo;
    
    @Autowired
    private RedisTemplate<String, List<Employee>> template;
    
    @Cacheable("employees")
    public List<Employee> getAllEmployees(){
        logger.info("getting employees from DB");
        return employeeRepo.findAll();
    }

    @CacheEvict(cacheNames="employees",allEntries=true) 
    public Employee saveEmployee (Employee employee){
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        Employee savedEmployee = employeeRepo.save(employee);
        log.info("Employee with id: {} saved successfully", employee.getId());
        return savedEmployee;
    }
}