package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.EmployeeDTO;
import com.hackerrank.sample.model.Employee;
import com.hackerrank.sample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO find(Integer id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        EmployeeDTO employeeDTO = null;

        if (employee != null) {
            employeeDTO = new EmployeeDTO(employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getDate(),
                    employee.getPhoneNumber());
        }

        return employeeDTO;
    }

    public List<EmployeeDTO> list() {
        List<EmployeeDTO> employees = new ArrayList<>();
        employeeRepository.findAllByOrderByIdAsc().forEach(e -> {
            EmployeeDTO employeeDTO = new EmployeeDTO(e.getId(),
                    e.getFirstName(),
                    e.getLastName(),
                    e.getDate(),
                    e.getPhoneNumber());
            employees.add(employeeDTO);
        });

        return employees;
    }

    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getDate(),
                employeeDTO.getPhoneNumber());

        employee = employeeRepository.save(employee);
        employeeDTO.setId(employee.getId());

        return employeeDTO;
    }

    public boolean remove(Integer id) {
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return false;
        }

        employeeRepository.deleteById(id);
        return true;
    }

}
