package com.learn.h2Demo.controller;

import com.learn.h2Demo.dto.EmpRequest;
import com.learn.h2Demo.entity.primary.EmployeeEntity;
import com.learn.h2Demo.entity.secondary.EmployeeEntitySecondary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoController {

    @Autowired
    com.learn.h2Demo.Repo.primary.Emp_Repo emp_repo1;

    @Autowired
    com.learn.h2Demo.Repo.secondary.EmpRepoSecondary emp_repoSecondary2;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/employees")
    public List<EmployeeEntity> getEmp() {
        return (List<EmployeeEntity>) emp_repo1.findAll();
    }

    @GetMapping("/employee/{id}")
    public Optional<EmployeeEntity> getEmpById(@PathVariable Long id) {
        return  emp_repo1.findById(id);
    }

    @PostMapping("/employee")
    public EmployeeEntity getEmpById(@RequestBody EmpRequest request) {
        EmployeeEntity emp = new EmployeeEntity(request.getEmail(), request.getFirstName(), request.getLastName());
        return emp_repo1.save(emp);
    }

    @GetMapping("/students")
    public List<EmployeeEntitySecondary> getAllStudents() {
        return (List<EmployeeEntitySecondary>) emp_repoSecondary2.findAll();
    }

    @GetMapping("/student/{id}")
    public Optional<EmployeeEntitySecondary> getStudentById(@PathVariable Long id) {
        return  emp_repoSecondary2.findById(id);
    }

    @PostMapping("/student")
    public EmployeeEntitySecondary postStudent(@RequestBody EmpRequest request) {
        EmployeeEntitySecondary emp = new EmployeeEntitySecondary(request.getEmail(), request.getFirstName(), request.getLastName());
        return emp_repoSecondary2.save(emp);
    }

//    @GetMapping("/student/{id}")
//    public Optional<StudentEntity> getStudentById(@PathVariable Long id) {
//        return  studentRepo.findById(id);
//    }
//
//    @GetMapping("/students")
//    public List<StudentEntity> getStudents() {
//        return (List<StudentEntity>) studentRepo.findAll();
//    }
//
//
//    @PostMapping("/student")
//    public StudentEntity getEmpById(@RequestBody StudentRequest request) {
//        StudentEntity student = new StudentEntity(request.getEmail(), request.getFirstName(), request.getLastName());
//        return studentRepo.save(student);
//    }

}

