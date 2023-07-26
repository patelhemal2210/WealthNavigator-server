package com.hemalpatel.wealthnavigator.exception;

import com.hemalpatel.balance.flow.model.BalanceNotifyingException;

public class InvalidDataInRequestException extends BalanceNotifyingException {

    public InvalidDataInRequestException(final String message) {
        super(message);
    }
}
