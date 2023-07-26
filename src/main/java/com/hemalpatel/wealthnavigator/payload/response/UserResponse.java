package com.hemalpatel.wealthnavigator.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hemalpatel.wealthnavigator.payload.dto.UserModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserResponse {

    @JsonProperty("users")
    private List<UserModel> users = null;
}
