package com.learn.h2MultipleDataSources.Repo.secondary;

import com.learn.h2MultipleDataSources.entity.secondary.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends CrudRepository<StudentEntity, Long> {

}
