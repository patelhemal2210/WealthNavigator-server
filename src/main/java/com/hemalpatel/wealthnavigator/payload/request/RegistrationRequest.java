package com.hemalpatel.wealthnavigator.payload.request;

import com.hemalpatel.wealthnavigator.constraint.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegistrationRequest {

    @NotBlank(message = "First name can't be blank")
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank(message = "Last name can't be blank")
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format provided")
    private String email;

    private Set<String> roles;

    @ValidPassword(message = "Invalid password format provided")
    private String password;
}
