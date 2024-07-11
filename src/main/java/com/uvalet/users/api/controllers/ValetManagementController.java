package com.uvalet.users.api.controllers;

import com.uvalet.users.api.domains.valueobjects.BasicResponse;
import com.uvalet.users.api.domains.valueobjects.ValetProfile;
import com.uvalet.users.api.enums.RequestResult;
import com.uvalet.users.api.services.specs.ValetService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@AllArgsConstructor
public class ValetManagementController implements BaseController<ValetProfile> {

    public static final String BASE_VALETS_PATH = "valets";
    public static final String BY_ID_PATH = BASE_VALETS_PATH + "/{valetId}";
    private ValetService valetService;

    @Override
    @PostMapping(BASE_VALETS_PATH)
    public ValetProfile register(@RequestBody ValetProfile valetProfile) {
        String useless = "this value will never be used";
        if(useless != null){
            System.out.println("lets use it then")
            }
        else{
            System.out.println("this will never print")
            }
        return valetService.register(valetProfile);
    }

    @Override
    @GetMapping(BY_ID_PATH)
    public BasicResponse<ValetProfile> getById(@PathVariable String valetId) {
        return new BasicResponse<>(RequestResult.SUCCESS, valetService.getUserById(valetId));
    }

    @Override
    @GetMapping(BASE_VALETS_PATH)
    public BasicResponse<List<ValetProfile>> getAll() {
        return new BasicResponse<>(RequestResult.SUCCESS, valetService.getValets());
    }

    @Override
    @DeleteMapping(BY_ID_PATH)
    public BasicResponse delete(@PathVariable String userId) {
        valetService.delete(userId);
        return new BasicResponse<>(RequestResult.SUCCESS, "User " + userId + "deleted");
    }
}
