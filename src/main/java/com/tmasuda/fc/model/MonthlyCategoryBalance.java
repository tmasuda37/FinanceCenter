package com.tmasuda.fc.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"account_public_id", "currency", "category_public_id", "year", "month"}))
public class MonthlyCategoryBalance {

    @Id
    @Column(name = "public_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long publicId;

    @ManyToOne
    public Account account;

    @Column
    public Currency currency;

    @ManyToOne
    public Category category;

    @Column
    public int year;

    @Column
    public int month;

    @Column
    public BigDecimal amount = BigDecimal.ZERO;

    @Column
    public BigDecimal budget = BigDecimal.ZERO;

    @Transient
    public Calendar calendar;

    @Transient
    public Boolean isBudgetTracking;

    public MonthlyCategoryBalance() {
        super();
    }

    public MonthlyCategoryBalance(Account account, Currency currency, Category category, int year, int month) {
        this.account = account;
        this.currency = currency;
        this.category = category;
        this.year = year;
        this.month = month;
    }

}
