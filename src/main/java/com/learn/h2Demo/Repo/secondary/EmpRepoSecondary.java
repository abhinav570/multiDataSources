package com.learn.h2Demo.Repo.secondary;

import com.learn.h2Demo.entity.secondary.EmployeeEntitySecondary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepoSecondary extends JpaRepository<EmployeeEntitySecondary, Long> {

}
