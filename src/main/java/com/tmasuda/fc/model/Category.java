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
    public boolean toExpense;

    @Column
    public boolean toTaxReturn;

    @Column
    public boolean toReimburse;

    public Category() {
    }

    public Category(CategoryBuilder categoryBuilder) {
        this.houseHold = categoryBuilder.houseHold;
        this.name = categoryBuilder.name;
        this.toExpense = categoryBuilder.toExpense;
        this.toTaxReturn = categoryBuilder.toTaxReturn;
        this.toReimburse = categoryBuilder.toReimburse;
    }

    public static class CategoryBuilder {
        private final HouseHold houseHold;
        private final String name;
        private boolean toExpense;
        private boolean toTaxReturn;
        private boolean toReimburse;

        public CategoryBuilder(HouseHold houseHold, String name, boolean toExpense) {
            this.houseHold = houseHold;
            this.name = name;
            this.toExpense = toExpense;
        }

        public CategoryBuilder toTaxReturn(boolean toTaxReturn) {
            this.toTaxReturn = toTaxReturn;
            return this;
        }

        public CategoryBuilder toReimburse(boolean toReimburse) {
            this.toReimburse = toReimburse;
            return this;
        }

        public Category build() {
            return new Category(this);
        }

    }

}