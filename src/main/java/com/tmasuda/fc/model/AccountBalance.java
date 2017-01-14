package com.tmasuda.fc.model;

import com.tmasuda.fc.model.key.AccountBalanceKey;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class AccountBalance {

    @EmbeddedId
    public AccountBalanceKey anAccountBalanceKey;

    @Column
    public BigDecimal amount = BigDecimal.ZERO;

    public AccountBalance() {
        super();
    }

    public AccountBalance(AccountBalanceKey anAccountBalanceKey) {
        super();
        this.anAccountBalanceKey = anAccountBalanceKey;
    }
}
