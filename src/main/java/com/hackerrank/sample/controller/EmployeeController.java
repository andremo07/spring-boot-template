package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.EmployeeDTO;
import com.hackerrank.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/")
    @ResponseBody
    public ResponseEntity get() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.list());
    }

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity get(@PathVariable int id) {
        EmployeeDTO employee = employeeService.find(id);
        if (employee != null) {
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity create(@RequestBody EmployeeDTO employee) {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(employeeService.create(employee));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity remove(@PathVariable int id) {
        return employeeService.remove(id) ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
