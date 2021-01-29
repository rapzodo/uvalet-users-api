package com.uvalet.users.api.restassured;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uvalet.users.api.UserApiApplication;
import com.uvalet.users.api.data.repositories.ValetRepository;
import com.uvalet.users.api.domains.valueobjects.ValetProfile;
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
public class ValetManagementControllerAssuredTest {

    @Resource
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    public static final String BASE_URL = "uvalet/user-management/valets";

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void shouldRegisterValet() throws JsonProcessingException {
        ValetProfile valetProfile = TestsUtil.buildAValet(null, "device1").mapToDtoOrDomain();
        getBasicJsonRequestSpecification()
                .body(objectMapper.writeValueAsString(valetProfile))
                .post(BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().ifValidationFails(LogDetail.ALL)
                .log().everything();
    }

    @Test
    public void shouldGetValetById() throws JsonProcessingException {
        ValetProfile valetProfile = TestsUtil.buildAValet(null, "device2").mapToDtoOrDomain();

        valetProfile = insertAValet(valetProfile);

        getBasicJsonRequestSpecification()
                .get(BASE_URL + "/{valetId}", valetProfile.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().ifValidationFails(LogDetail.ALL)
                .log().body();
    }

    private ValetProfile insertAValet(ValetProfile valetProfile) throws JsonProcessingException {
        return getBasicJsonRequestSpecification()
                .body(objectMapper.writeValueAsString(valetProfile))
                .post(BASE_URL)
                .then()
                .log().everything()
                .statusCode(HttpStatus.OK.value())
                .log().ifValidationFails(LogDetail.ALL)
                .extract()
                .as(ValetProfile.class);
    }


    @After
    public void cleanUp() {
        final ValetRepository bean = webApplicationContext.getBean(ValetRepository.class);
        bean.deleteAll();
    }

    private RequestSpecification getBasicJsonRequestSpecification() {
        return given().when()
                .port(8090)
                .contentType(ContentType.JSON);
    }
}

