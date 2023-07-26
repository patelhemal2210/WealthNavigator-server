package com.hemalpatel.wealthnavigator.exception;

import com.hemalpatel.balance.flow.model.BalanceNotifyingException;

public class RoleNotFoundException extends BalanceNotifyingException {

    public RoleNotFoundException(final String message) {
        super(message);
    }
}
