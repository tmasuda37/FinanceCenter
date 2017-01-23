package com.tmasuda.fc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Place {
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

    public Place() {
    }

    public Place(PlaceBuilder placeBuilder) {
        this.houseHold = placeBuilder.houseHold;
        this.name = placeBuilder.name;
    }

    public static class PlaceBuilder {
        private final HouseHold houseHold;
        private final String name;

        public PlaceBuilder(HouseHold houseHold, String name) {
            this.houseHold = houseHold;
            this.name = name;
        }

        public Place build() {
            return new Place(this);
        }
    }
}