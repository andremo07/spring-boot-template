package com.hackerrank.sample.repository;

import com.hackerrank.sample.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    public List<Employee> findAllByOrderByIdAsc();
}
