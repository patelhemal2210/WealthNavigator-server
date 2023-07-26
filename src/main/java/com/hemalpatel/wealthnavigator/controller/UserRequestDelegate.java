package com.hemalpatel.wealthnavigator.controller;

import com.hemalpatel.balance.flow.model.RestRequest;
import com.hemalpatel.balance.flow.model.RestResponse;
import com.hemalpatel.balance.flow.operation.IOperation;
import com.hemalpatel.wealthnavigator.payload.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRequestDelegate {

    @Autowired
    @Qualifier("getUsersOperation")
    private IOperation<RestRequest<Void>, RestResponse<UserResponse>> getUsersOperation;

    public ResponseEntity<RestResponse<UserResponse>> getUsers(RestRequest<Void> request) {
        return getUsersOperation.handle(request).entity(HttpStatus.OK);
    }
}
