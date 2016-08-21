package com.tmasuda.fc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Category {
	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long publicID;

	@ManyToOne
	public HouseHold household;

	@Column
	@Enumerated(EnumType.STRING)
	public CategoryApplyTo categoryApplyTo;

	@Column
	public String name;

	@Column
	public boolean toExpense;

	@Column
	public boolean toTaxReturn;

	@Column
	public boolean toReimburse;

	public Category() {
	}

	public Category(HouseHold household, CategoryApplyTo categoryApplyTo, String name, boolean toExpense) {
		super();
		this.household = household;
		this.categoryApplyTo = categoryApplyTo;
		this.name = name;
		this.toExpense = toExpense;
	}

}