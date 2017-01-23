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

    public Event(EventBuilder eventBuilder) {
        this.houseHold = eventBuilder.houseHold;
        this.name = eventBuilder.name;
    }

    public static class EventBuilder {
        private final HouseHold houseHold;
        private final String name;

        public EventBuilder(HouseHold houseHold, String name) {
            this.houseHold = houseHold;
            this.name = name;
        }

        public Event build() {
            return new Event(this);
        }
    }
}