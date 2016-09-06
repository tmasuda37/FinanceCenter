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

	public Category(CategoryBuilder categoryBuilder) {
		this.houseHold = categoryBuilder.houseHold;
		this.categoryApplyTo = categoryBuilder.categoryApplyTo;
		this.name = categoryBuilder.name;
		this.toExpense = categoryBuilder.toExpense;
		this.toTaxReturn = categoryBuilder.toTaxReturn;
		this.toReimburse = categoryBuilder.toReimburse;
	}

	public static class CategoryBuilder {
		private final HouseHold houseHold;
		private final CategoryApplyTo categoryApplyTo;
		private final String name;
		private boolean toExpense;
		private boolean toTaxReturn;
		private boolean toReimburse;

		public CategoryBuilder(HouseHold houseHold, CategoryApplyTo categoryApplyTo, String name, boolean toExpense) {
			this.houseHold = houseHold;
			this.categoryApplyTo = categoryApplyTo;
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