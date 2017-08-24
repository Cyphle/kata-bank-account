package fr.bank.account;

import java.math.BigDecimal;
import java.math.MathContext;

public class Account {
    public BigDecimal getCurrentBalance() {
        return new BigDecimal(0, MathContext.DECIMAL64);
    }
}
