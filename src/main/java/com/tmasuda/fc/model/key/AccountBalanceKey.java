package com.tmasuda.fc.model.key;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.CategoryApplyTo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Currency;

@Embeddable
public class AccountBalanceKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    public Account account;

    @Column
    public Currency currency;

    @Column
    @Enumerated(EnumType.STRING)
    public CategoryApplyTo cateogoryApplyTo;

    public AccountBalanceKey() {
    }

    public AccountBalanceKey(Account account, Currency currency, CategoryApplyTo cateogoryApplyTo) {
        this.account = account;
        this.currency = currency;
        this.cateogoryApplyTo = cateogoryApplyTo;
    }

}
