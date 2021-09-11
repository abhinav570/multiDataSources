package com.learn.h2MultipleDataSources;

import com.learn.h2MultipleDataSources.Repo.primary.EmpRepoPrimary;
import com.learn.h2MultipleDataSources.Repo.secondary.EmpRepoSecondary;
import com.learn.h2MultipleDataSources.entity.primary.EmployeeEntity;
import com.learn.h2MultipleDataSources.entity.secondary.EmployeeEntitySecondary;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class multiRepoTest {

    @Autowired
    private EmpRepoSecondary repoSecondary;

    @Autowired
    private EmpRepoPrimary repoPrimary;

    @Test
    public void souldSaveSecondary() {
        int countBefore = repoSecondary.findAll().size();
        EmployeeEntitySecondary emp = new EmployeeEntitySecondary("abhi@gmail.com", "abhi", "sadhu");
        repoSecondary.save(emp);
        int afterCount = repoSecondary.findAll().size();
        System.out.println(afterCount);
        Assert.assertEquals(afterCount - countBefore, 1);
    }

    @Test
    public void souldSavePrimary() {
        int countBefore = repoPrimary.findAll().size();
        EmployeeEntity emp = new EmployeeEntity("second@gmail.com", "second", "last");
        repoPrimary.save(emp);
        int afterCount = repoPrimary.findAll().size();
        System.out.println(afterCount);
        Assert.assertEquals(afterCount - countBefore, 1);
    }

}
