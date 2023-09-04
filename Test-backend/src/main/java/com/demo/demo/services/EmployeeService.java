package com.demo.demo.services;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.demo.models.Employee;
import com.demo.demo.models.Response;
import com.demo.demo.repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("employees", employeeRepository.findAll()))
                        .message("employees data retrieved successfully")
                        .statusCode(200)
                        .count(employeeRepository.count())
                        .build()
                        
        );
    }

    public ResponseEntity<Response> getOne(Long id) {
        Employee emp = this.employeeRepository.findById(id).orElse(null);
        
        if (emp == null) {
            return ResponseEntity.status(404).body(
                    Response.builder()
                            .timestamp(LocalDateTime.now())
                            .error("Employee not found")
                            .statusCode(404)
                            .build());
        }
        
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("employee", emp))
                        .message("employee data retrieved successfully")
                        .statusCode(200)
                        .count(1L)
                        .build());
    }

    public ResponseEntity<Response> create(Employee employee) {
        Employee emp;
        try {
            emp = this.employeeRepository.save(employee);
            return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("employee", emp))
                        .message("employee created successfully")
                        .count(1L)
                        .statusCode(201)
                        .build()); 
        } catch (Exception e) {
            return ResponseEntity.status(400).body(
                Response.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(400)
                .error(e.getMessage())
                .build());
        }
    }

    public ResponseEntity<Response> update(Long id, Employee employee) {
        Employee emp = this.employeeRepository.findById(id).orElse(null);

        if (emp == null) {
            return ResponseEntity.status(404).body(
                Response.builder()
                .timestamp(LocalDateTime.now())
                .error("Employee not found")
                .statusCode(404)
                .build());
        }

        employee.setId(id);

        try {
            emp = this.employeeRepository.save(employee);

            return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("employee", emp))
                        .message("employee updated successfully")
                        .count(1L)
                        .statusCode(202)
                        .build());
        } catch (Exception e) {
            return ResponseEntity.status(400).body(
                Response.builder()
                .timestamp(LocalDateTime.now())
                .statusCode(400)
                .error(e.getMessage())
                .build());
        }
    }
    
    public ResponseEntity<Response> delete(Long id) {
        Employee emp = this.employeeRepository.findById(id).orElse(null);

        if (emp == null) {
           return ResponseEntity.status(404).body(
                Response.builder()
                .timestamp(LocalDateTime.now())
                .error("Employee not found")
                .statusCode(404)
                .build()); 
        }

        try {
            this.employeeRepository.deleteById(id);
            return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("employee", emp))
                        .message("employee deleted successfully")
                        .statusCode(200)
                        .count(1L)
                        .build());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                Response.builder()
                .timestamp(LocalDateTime.now())
                .error(e.getMessage())
                .statusCode(500)
                .build());
        }
    }
    
}
