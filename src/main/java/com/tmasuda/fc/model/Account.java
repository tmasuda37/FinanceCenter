package com.tmasuda.fc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Account {

    @JsonIgnore
    @Id
    @Column(name = "public_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long publicId;

    @NaturalId
    @Column
    public String snsId;

    @ManyToOne
    @JoinColumn(name = "house_hold_id", nullable = true)
    public HouseHold houseHold;

    public Account() {
    }

}