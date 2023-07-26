package com.hemalpatel.wealthnavigator.payload.mapper;

import com.hemalpatel.wealthnavigator.domain.Role;
import com.hemalpatel.wealthnavigator.domain.User;
import com.hemalpatel.wealthnavigator.payload.dto.UserModel;
import com.hemalpatel.wealthnavigator.payload.request.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toUser(RegistrationRequest request, List<Role> roles) {
        return new User()
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setEmail(request.getEmail())
                .setPassword(passwordEncoder.encode(request.getPassword()))
                .setRoles(roles);
    }

    public UserModel toUserModel(User user) {

        return new UserModel()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setEnable(user.isEnabled());
    }

    public List<UserModel> toUserModelList(List<User> users) {

        return users.stream().map(this::toUserModel)
                .collect(Collectors.toList());
    }
}
