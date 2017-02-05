package com.tmasuda.fc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Event {
    @Id
    @Column(name = "public_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long publicId;

    @ManyToOne
    public HouseHold houseHold;

    @Column
    @Size(min = 1)
    @NotNull(message = "Name cannot be null or empty.")
    public String name;

    public Event() {
    }

}