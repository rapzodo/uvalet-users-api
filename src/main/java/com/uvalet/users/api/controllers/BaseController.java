package com.uvalet.users.api.controllers;

import com.uvalet.users.api.domains.valueobjects.BasicResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("uvalet/user-management")
public interface BaseController<T> {

    @ApiOperation(value = "retrieve all users", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<List<T>> getAll();

    @ApiOperation(value = "register user", produces = MediaType.APPLICATION_JSON_VALUE)
    T register(@RequestBody T t);


    @ApiOperation(value = "find a user based on ID", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse<T> getById(@PathVariable String id);

    @ApiOperation(value = "delete user based on ID", produces = MediaType.APPLICATION_JSON_VALUE)
    BasicResponse delete(@PathVariable String id);
}
