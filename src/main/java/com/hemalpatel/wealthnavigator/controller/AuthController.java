package com.hemalpatel.wealthnavigator.controller;

import com.hemalpatel.balance.flow.model.RestRequest;
import com.hemalpatel.balance.flow.model.RestResponse;
import com.hemalpatel.wealthnavigator.payload.request.LoginRequest;
import com.hemalpatel.wealthnavigator.payload.request.RegistrationRequest;
import com.hemalpatel.wealthnavigator.payload.response.LoginResponse;
import com.hemalpatel.wealthnavigator.payload.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthRequestDelegate authRequestDelegate;

    @PostMapping(value = "/signin",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<LoginResponse>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        RestRequest<LoginRequest> request = RestRequest
                .<LoginRequest>builder()
                .request(loginRequest)
                .build();

        return authRequestDelegate.authenticateUser(request);
    }

    @PostMapping(value = "/signup",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestResponse<RegistrationResponse>> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest) {

        RestRequest<RegistrationRequest> request = RestRequest
                .<RegistrationRequest>builder()
                .request(registrationRequest)
                .build();

        return authRequestDelegate.registerUser(request);
    }
}
