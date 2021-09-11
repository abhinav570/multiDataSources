package com.learn.h2MultipleDataSources.Repo.primary;

import com.learn.h2MultipleDataSources.entity.primary.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepoPrimary extends JpaRepository<EmployeeEntity, Long> {

}
