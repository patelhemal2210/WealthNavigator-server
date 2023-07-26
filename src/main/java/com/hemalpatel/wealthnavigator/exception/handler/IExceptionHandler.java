package com.hemalpatel.wealthnavigator.exception.handler;

import com.hemalpatel.balance.flow.exception.ExceptionMessageHandler;
import com.hemalpatel.balance.flow.model.Notification;
import com.hemalpatel.balance.flow.model.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface IExceptionHandler {

    default ResponseEntity<RestResponse<Void>> handleException(Exception exception, String code, HttpStatus httpStatus) {
        String message = ExceptionMessageHandler.getMessage(code);
        Notification notification = Notification.builder().code(code)
                .message(message.isEmpty() ? exception.getMessage() : message)
                .metadata("errorMessage", exception.getMessage())
                .build();
        return RestResponse.error(notification).entity(httpStatus);
    }

    default ResponseEntity<RestResponse<Void>> handleException(String errorMessages, String code, HttpStatus httpStatus) {

        String message = ExceptionMessageHandler.getMessage(code);
        Notification notification = Notification.builder().code(code)
                .message(message.isEmpty() ? httpStatus.toString() : message)
                .metadata("errorMessage", errorMessages)
                .build();

        return RestResponse.error(notification).entity(httpStatus);
    }
}
