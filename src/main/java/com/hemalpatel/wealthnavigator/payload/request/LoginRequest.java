package com.hemalpatel.wealthnavigator.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Password can't be blank")
    private String password;
}
