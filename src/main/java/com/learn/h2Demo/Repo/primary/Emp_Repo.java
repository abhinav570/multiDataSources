package com.learn.h2Demo.Repo.primary;

import com.learn.h2Demo.entity.primary.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Emp_Repo extends JpaRepository<EmployeeEntity, Long> {

}
