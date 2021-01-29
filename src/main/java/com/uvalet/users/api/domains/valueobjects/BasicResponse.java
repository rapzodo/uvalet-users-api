package com.uvalet.users.api.domains.valueobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uvalet.users.api.enums.RequestResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class BasicResponse<T> {

    private RequestResult requestResult;
    @JsonIgnore
    @Getter
    private Throwable error;
    private T body;
    private HttpStatus status;
    private int statusCode;

    public BasicResponse() {
        this.requestResult = RequestResult.SUCCESS;
    }

    public BasicResponse(RequestResult requestResult, T body) {
        this.requestResult = requestResult;
        this.body = body;
    }

    public BasicResponse(RequestResult requestResult, T body, HttpStatus status) {
        this.requestResult = requestResult;
        this.status = status;
        this.body = body;
    }

    public static BasicResponse success(Object body) {
        return BasicResponse.builder()
                .body(body)
                .requestResult(RequestResult.SUCCESS)
                .build();
    }

    public String getStatus(){
        if (Objects.nonNull(status)){
            return status.getReasonPhrase();
        }
        return HttpStatus.OK.getReasonPhrase();
    }

    public String getException(){
        if (Objects.nonNull(error)){
            return error.getClass().getName();
        }
        return null;
    }

    public String getErrorMessage(){
        if(error != null){
            return error.getMessage();
        }
        return null;
    }

    public int getStatusCode(){
        if(Objects.nonNull(status)){
            return status.value();
        }
        return HttpStatus.OK.value();
    }

}
