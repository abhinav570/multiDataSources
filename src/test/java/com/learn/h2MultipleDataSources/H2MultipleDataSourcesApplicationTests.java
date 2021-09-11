package com.learn.h2MultipleDataSources;

import com.learn.h2MultipleDataSources.config.PrimaryDataSourceConfiguration;
import com.learn.h2MultipleDataSources.config.SecondaryDataSourceConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {H2MultipleDataSourcesApplication.class, PrimaryDataSourceConfiguration.class, SecondaryDataSourceConfiguration.class})
@ActiveProfiles("test")
class H2MultipleDataSourcesApplicationTests {

}
