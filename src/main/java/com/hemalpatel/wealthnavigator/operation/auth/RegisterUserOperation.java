package com.hemalpatel.wealthnavigator.operation.auth;

import com.hemalpatel.balance.flow.model.RestRequest;
import com.hemalpatel.balance.flow.model.RestResponse;
import com.hemalpatel.balance.flow.operation.IOperation;
import com.hemalpatel.wealthnavigator.domain.ERole;
import com.hemalpatel.wealthnavigator.domain.Role;
import com.hemalpatel.wealthnavigator.domain.User;
import com.hemalpatel.wealthnavigator.exception.InvalidDataInRequestException;
import com.hemalpatel.wealthnavigator.exception.RoleNotFoundException;
import com.hemalpatel.wealthnavigator.payload.mapper.UserMapper;
import com.hemalpatel.wealthnavigator.payload.request.RegistrationRequest;
import com.hemalpatel.wealthnavigator.payload.response.RegistrationResponse;
import com.hemalpatel.wealthnavigator.repository.RoleRepository;
import com.hemalpatel.wealthnavigator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class RegisterUserOperation implements IOperation<RestRequest<RegistrationRequest>, RestResponse<RegistrationResponse>> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public RestResponse<RegistrationResponse> handle(RestRequest<RegistrationRequest> request) {

        RegistrationRequest registrationRequest = request.getRequest();

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new InvalidDataInRequestException("Email already taken.");
        }

        User user = createUser(registrationRequest);
        userRepository.save(user);

        return RestResponse.valueOf(new RegistrationResponse());
    }

    private User createUser(RegistrationRequest request) {
        Set<String> strRoles = request.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RoleNotFoundException("Role not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach((role) -> {
                if (ERole.fromString(role) == ERole.ROLE_ADMIN) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RoleNotFoundException("Role not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RoleNotFoundException("Role not found."));
                    roles.add(userRole);
                }
            });
        }

        return userMapper.toUser(request, new ArrayList<>(roles));
    }
}
