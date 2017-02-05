package com.tmasuda.fc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"house_hold_house_hold_id", "name", "toExpense"}))
public class Category {

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

    @Column
    public Boolean toExpense;

    @Column
    public Boolean toTaxReturn;

    @Column
    public Boolean toReimburse;

    @Column
    public Boolean toIgnoreCategoryBalance = false;

    @Column
    public Boolean isBudgetTracking = false;

    public Category() {
    }

}