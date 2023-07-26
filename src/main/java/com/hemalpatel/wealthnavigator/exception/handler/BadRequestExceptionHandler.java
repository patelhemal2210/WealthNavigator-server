package com.hemalpatel.wealthnavigator.exception.handler;

import com.hemalpatel.balance.flow.model.RestResponse;
import com.hemalpatel.wealthnavigator.exception.InvalidDataInRequestException;
import com.hemalpatel.wealthnavigator.exception.RoleNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@ControllerAdvice
public class BadRequestExceptionHandler implements IExceptionHandler {

    @ExceptionHandler({InvalidDataInRequestException.class})
    public final ResponseEntity<RestResponse<Void>> handleInvalidDataInRequestException(InvalidDataInRequestException e) {
        return handleException(e, "400_001", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<RestResponse<Void>> handleConstraintViolationException(ConstraintViolationException e) {
        return handleException(e, "400_002", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<RestResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return handleException(errorMessage, "400_003", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RoleNotFoundException.class})
    public final ResponseEntity<RestResponse<Void>> handleRoleNotFoundException(RoleNotFoundException e) {
        return handleException(e, "400_004", HttpStatus.BAD_REQUEST);
    }
}
