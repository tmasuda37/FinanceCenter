package com.tmasuda.fc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

@Entity
public class Transaction {

    @Id
    @Column(name = "public_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long publicId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    public Account account;

    @Column
    public Calendar calendar;

    @Column
    @NotNull(message = "Amount cannot be null or empty.")
    public BigDecimal amount;

    @Column
    public Currency currency;

    @ManyToOne(optional = false)
    public Category category;

    @ManyToOne
    public Place place;

    @ManyToOne
    public Event event;

    @Column
    public String description;

    @Override
    public String toString() {
        return "Transaction{" +
                ", account=" + account +
                ", calendar=" + calendar +
                ", amount=" + amount +
                ", currency=" + currency +
                ", category=" + category +
                ", place=" + place +
                ", event=" + event +
                ", description='" + description + '\'' +
                '}';
    }

}