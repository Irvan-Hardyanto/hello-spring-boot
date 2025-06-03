package com.example.demo.employee;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An entity class represents a table in a relational database
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "employee")
public class Employee implements Serializable{
    @Id
    private Integer id;

    @NotEmpty(message = "First name cannot be empty")
    private String firstName;
    private String lastName;
    private Integer age;
    // private String designation;
    // private String phoneNumber;
    // private LocalDate joinedOn;
    // private String address;
    // private LocalDate dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}