package com.tmasuda.fc.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"event_public_id", "currency"}))
public class EventBalance {

    @Id
    @Column(name = "public_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long publicId;

    @ManyToOne
    public Event event;

    @Column
    public Currency currency;

    @Column
    public BigDecimal amount = BigDecimal.ZERO;

    public EventBalance() {
        super();
    }

    public EventBalance(Event event, Currency currency) {
        this.event = event;
        this.currency = currency;
    }

}
