package com.uvalet.users.api.restassured;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uvalet.users.api.UserApiApplication;
import com.uvalet.users.api.data.repositories.CustomerRepository;
import com.uvalet.users.api.domains.entities.Customer;
import com.uvalet.users.api.domains.valueobjects.CustomerProfile;
import com.uvalet.users.api.factory.TestsUtil;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("local")
public class CustomerManagementControllerAssuredTest {

    @Resource
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    public static final String USERS_BASE_URL = "uvalet/user-management/customers";

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void shouldRegisterCustomer() throws JsonProcessingException {
        CustomerProfile userDto = TestsUtil.buildACustomer("1000", "device1").mapToDtoOrDomain();
        getBasicJsonRequestSpecification()
                .body(objectMapper.writeValueAsString(userDto))
                .log().body()
                .post(USERS_BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().ifValidationFails(LogDetail.ALL)
                .log().body();
    }

    @Test
    public void shouldGetCustomerById() throws JsonProcessingException {
        CustomerProfile userDto = TestsUtil.buildACustomer("1000", "device1").mapToDtoOrDomain();
        CustomerProfile savedCustomer = insertACustomer(userDto);

        getBasicJsonRequestSpecification()
                .log().uri()
                .get(USERS_BASE_URL + "/{customerId}", savedCustomer.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().ifValidationFails(LogDetail.ALL)
                .log().body();
    }

    private CustomerProfile insertACustomer(CustomerProfile userDto) throws JsonProcessingException {
        return getBasicJsonRequestSpecification()
                .body(objectMapper.writeValueAsString(userDto))
                .post(USERS_BASE_URL)
                .prettyPeek()
                .as(CustomerProfile.class);
    }

    @Test
    public void shouldRetrieveAllCustomers() throws JsonProcessingException, InterruptedException {
        insertACustomer(TestsUtil.buildACustomer("", "d1").mapToDtoOrDomain());
        final Customer c2 = TestsUtil.buildACustomer("", "d2");
        c2.setEmail("joedoe2@m.com");
        insertACustomer(c2.mapToDtoOrDomain());

        getBasicJsonRequestSpecification()
                .get(USERS_BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().ifValidationFails().log().body();
    }


    @After
    public void cleanUp() {
        final CustomerRepository bean = webApplicationContext.getBean(CustomerRepository.class);
        bean.deleteAll();
    }

    private RequestSpecification getBasicJsonRequestSpecification() {
        return given().when()
                .port(8090)
                .contentType(ContentType.JSON);
    }
}

