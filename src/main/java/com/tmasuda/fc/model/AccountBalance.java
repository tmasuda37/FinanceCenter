package com.tmasuda.fc.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"account_public_id", "currency", "categoryApplyTo"}))
public class AccountBalance {

    @Id
    @Column(name = "public_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long publicId;

    @ManyToOne
    public Account account;

    @Column
    public Currency currency;

    @Column
    @Enumerated(EnumType.STRING)
    public CategoryApplyTo categoryApplyTo;

    @Column
    public BigDecimal amount = BigDecimal.ZERO;

    public AccountBalance() {
        super();
    }

    public AccountBalance(Account account, Currency currency, CategoryApplyTo categoryApplyTo) {
        this.account = account;
        this.currency = currency;
        this.categoryApplyTo = categoryApplyTo;
    }

}
