package com.hemalpatel.wealthnavigator.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hemalpatel.wealthnavigator.domain.Role;
import com.hemalpatel.wealthnavigator.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
public class LoginResponse {

    @JsonProperty("user")
    private LoginUserDetails userDetails;

    @JsonProperty("access_token")
    private String token;

    public LoginResponse(User user, String token) {
        this.userDetails = new LoginUserDetails()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setRoles(user.getRoles());

        this.token = token;
    }

    @Data
    @Accessors(chain = true)
    public static class LoginUserDetails {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private List<Role> roles;
    }
}
