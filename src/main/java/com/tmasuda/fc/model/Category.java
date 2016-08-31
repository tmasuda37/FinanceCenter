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

	private static final CategoryApplyTo DEFAULT_CATEGORY_APPLY_TO = CategoryApplyTo.Wallet;

	private static final boolean DEFAULT_TO_EXPENSE = true;

	private static final boolean DEFAULT_TO_TAX_RETURN = false;

	private static final boolean DEFAULT_TO_REIMBURSE = false;

	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long publicId;

	@ManyToOne
	public HouseHold houseHold;

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

	public Category(Long publicId) {
		this.publicId = publicId;
	}

	public Category(HouseHold houseHold, String name) {
		this(houseHold, DEFAULT_CATEGORY_APPLY_TO, name, DEFAULT_TO_EXPENSE, DEFAULT_TO_TAX_RETURN, DEFAULT_TO_REIMBURSE);
	}

	public Category(HouseHold houseHold, CategoryApplyTo categoryApplyTo, String name, boolean toExpense, boolean toTaxReturn, boolean toReimburse) {
		this.houseHold = houseHold;
		this.categoryApplyTo = categoryApplyTo;
		this.name = name;
		this.toExpense = toExpense;
		this.toTaxReturn = toTaxReturn;
		this.toReimburse = toReimburse;
	}

}