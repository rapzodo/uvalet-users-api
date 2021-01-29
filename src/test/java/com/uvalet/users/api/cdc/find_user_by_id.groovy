package com.uvalet.users.api.cdc

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should find user"

    request {
        url "users/1"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
                id: "1",
                email: "d@mail.com",
                firstName: "danilo"
        )
    }

}