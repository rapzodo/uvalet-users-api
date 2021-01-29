package com.uvalet.users.api.data.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uvalet.users.api.UserApiApplication;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@DataMongoTest
@RunWith(SpringRunner.class)
@ComponentScan("com.valetme")
@ContextConfiguration(classes = UserApiApplication.class)
//@Ignore
public class AbstractRepositoryTest {

    protected ObjectMapper objectMapper = new ObjectMapper();

}
