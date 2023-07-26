package com.hemalpatel.wealthnavigator.operation.auth;

import com.hemalpatel.balance.flow.model.RestRequest;
import com.hemalpatel.balance.flow.model.RestResponse;
import com.hemalpatel.balance.flow.operation.IOperation;
import com.hemalpatel.wealthnavigator.configuration.security.jwt.JwtUtils;
import com.hemalpatel.wealthnavigator.domain.User;
import com.hemalpatel.wealthnavigator.model.UserPrincipal;
import com.hemalpatel.wealthnavigator.payload.request.LoginRequest;
import com.hemalpatel.wealthnavigator.payload.response.LoginResponse;
import com.hemalpatel.wealthnavigator.repository.UserRepository;
import com.hemalpatel.wealthnavigator.service.AuthAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticateUserOperation implements IOperation<RestRequest<LoginRequest>, RestResponse<LoginResponse>> {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthAttemptService authAttemptService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RestResponse<LoginResponse> handle(RestRequest<LoginRequest> request) {

        final LoginRequest loginRequest = request.getRequest();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            authAttemptService.successAuthEvent(userPrincipal.getUser());

            return RestResponse.valueOf(new LoginResponse(userPrincipal.getUser(), jwt));
        } catch (UsernameNotFoundException e) {
            throw e;
        } catch (AuthenticationException e) {
            Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
            userOptional.ifPresent(user -> authAttemptService.failedAuthEvent(user));
            throw e;
        }
    }
}
