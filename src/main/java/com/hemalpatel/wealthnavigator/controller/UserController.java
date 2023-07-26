package com.hemalpatel.wealthnavigator.controller;

import com.hemalpatel.balance.flow.model.RestRequest;
import com.hemalpatel.balance.flow.model.RestResponse;
import com.hemalpatel.wealthnavigator.payload.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRequestDelegate userRequestDelegate;

    @GetMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<UserResponse>> getUsers() {
        RestRequest<Void> request = RestRequest.<Void>builder().build();

        return userRequestDelegate.getUsers(request);
    }
}
