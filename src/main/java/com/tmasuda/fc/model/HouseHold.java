package com.tmasuda.fc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class HouseHold {

    @Id
    @Column
    public String houseHoldId;

    @JsonIgnore
    @OneToMany(mappedBy = "houseHold")
    public Set<Account> accounts = new HashSet<Account>();

    public HouseHold() {
    }

    public HouseHold(String houseHoldId) {
        this.houseHoldId = houseHoldId;
    }

}