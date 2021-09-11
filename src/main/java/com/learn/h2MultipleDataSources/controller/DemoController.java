package com.learn.h2MultipleDataSources.controller;

import com.learn.h2MultipleDataSources.dto.EmpRequest;
import com.learn.h2MultipleDataSources.entity.primary.EmployeeEntity;
import com.learn.h2MultipleDataSources.entity.secondary.EmployeeEntitySecondary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    com.learn.h2MultipleDataSources.Repo.primary.EmpRepoPrimary repoPrimary;

    @Autowired
    com.learn.h2MultipleDataSources.Repo.secondary.EmpRepoSecondary repoSecondary;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/employeesPrimary")
    public List<EmployeeEntity> getAllEmployeesPrimary() {
        return repoPrimary.findAll();
    }

    @GetMapping("/employeePrimary/{id}")
    public Optional<EmployeeEntity> getEmpPrimaryById(@PathVariable Long id) {
        return repoPrimary.findById(id);
    }

    @PostMapping("/employeePrimary")
    public EmployeeEntity postEmployeePrimary(@RequestBody EmpRequest request) {
        EmployeeEntity emp = new EmployeeEntity(request.getEmail(), request.getFirstName(), request.getLastName());
        return repoPrimary.save(emp);
    }

    @GetMapping("/employeesSecondary")
    public List<EmployeeEntitySecondary> getAllEmployeesSecondary() {
        return repoSecondary.findAll();
    }

    @GetMapping("/employeeSecondary/{id}")
    public Optional<EmployeeEntitySecondary> getEmployeeSecondaryById(@PathVariable Long id) {
        return repoSecondary.findById(id);
    }

    @PostMapping("/employeeSecondary")
    public EmployeeEntitySecondary postEmployeeSecondary(@RequestBody EmpRequest request) {
        EmployeeEntitySecondary emp = new EmployeeEntitySecondary(request.getEmail(), request.getFirstName(), request.getLastName());
        return repoSecondary.save(emp);
    }

}

