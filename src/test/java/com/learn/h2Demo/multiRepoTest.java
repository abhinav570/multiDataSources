package com.learn.h2Demo;

import com.learn.h2Demo.Repo.primary.Emp_Repo;
import com.learn.h2Demo.Repo.secondary.EmpRepoSecondary;
import com.learn.h2Demo.entity.secondary.EmployeeEntitySecondary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class multiRepoTest {

    @Autowired
    private EmpRepoSecondary empRepoSecondary;

    @Autowired
    private Emp_Repo emp_repo;

    @Test
    public void souldSaveSecondary(){
        Iterable<EmployeeEntitySecondary> countBefore = empRepoSecondary.findAll();

    }

}
