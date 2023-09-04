package com.demo.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.models.Employee;
import com.demo.demo.models.Response;
import com.demo.demo.services.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {
    
    private final EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<Response> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOne(@PathVariable("id") Long id) {
        return employeeService.getOne(id);
    }

    @PostMapping("")
    public ResponseEntity<Response> create(@RequestBody() Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") Long id, @RequestBody() Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        return employeeService.delete(id);
    }
}
