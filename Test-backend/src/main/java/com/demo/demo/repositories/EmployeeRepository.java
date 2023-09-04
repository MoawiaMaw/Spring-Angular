package com.demo.demo.repositories;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.demo.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // public List<Employee> getAll();

    // public Employee getOne(Long id);

    // public Employee create(Employee employee);

    // public Employee update(Long id, Employee employee);

    // public Employee delete(Long id);
}
