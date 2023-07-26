package com.hemalpatel.wealthnavigator.controller;

import com.hemalpatel.balance.flow.model.RestRequest;
import com.hemalpatel.balance.flow.model.RestResponse;
import com.hemalpatel.balance.flow.operation.IOperation;
import com.hemalpatel.wealthnavigator.payload.request.LoginRequest;
import com.hemalpatel.wealthnavigator.payload.request.RegistrationRequest;
import com.hemalpatel.wealthnavigator.payload.response.LoginResponse;
import com.hemalpatel.wealthnavigator.payload.response.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthRequestDelegate {

    @Autowired
    @Qualifier("authenticateUserOperation")
    private IOperation<RestRequest<LoginRequest>, RestResponse<LoginResponse>> authenticateUserOperation;

    @Autowired
    @Qualifier("registerUserOperation")
    private IOperation<RestRequest<RegistrationRequest>, RestResponse<RegistrationResponse>> registerUserOperation;

    public ResponseEntity<RestResponse<LoginResponse>> authenticateUser(RestRequest<LoginRequest> request) {
        return authenticateUserOperation.handle(request).entity(HttpStatus.OK);
    }

    public ResponseEntity<RestResponse<RegistrationResponse>> registerUser(RestRequest<RegistrationRequest> request) {
        return registerUserOperation.handle(request).entity(HttpStatus.OK);
    }
}
