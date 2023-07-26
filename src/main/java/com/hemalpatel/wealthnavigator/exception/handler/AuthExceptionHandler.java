package com.hemalpatel.wealthnavigator.exception.handler;

import com.hemalpatel.balance.flow.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler implements IExceptionHandler {

    @ExceptionHandler({BadCredentialsException.class})
    public final ResponseEntity<RestResponse<Void>> handleBadCredentialsException(BadCredentialsException e) {
        return handleException(e, "401_001", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public final ResponseEntity<RestResponse<Void>> handleUserNotFoundException(UsernameNotFoundException e) {
        return handleException("User not found : " + e.getMessage(), "401_002", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({AuthenticationException.class})
    public final ResponseEntity<RestResponse<Void>> handleAuthenticationException(AuthenticationException e) {
        return handleException(e, "401_003", HttpStatus.UNAUTHORIZED);
    }
}
