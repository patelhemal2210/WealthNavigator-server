package com.hemalpatel.wealthnavigator.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistrationResponse {

    @JsonProperty("message")
    private String message;

    public RegistrationResponse() {
        this.message = "Registration successfully";
    }
}
