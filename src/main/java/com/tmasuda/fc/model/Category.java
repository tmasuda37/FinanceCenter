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
	public Home home;

	@Column
	@Enumerated(EnumType.STRING)
	public CategoryApplyTo cateogoryApplyTo;

	@Column
	public String name;

	@Column
	public boolean expense;

	public Category() {
	}

	public Category(Home home, String name, CategoryApplyTo cateogoryApplyTo, boolean expense) {
		super();
		this.home = home;
		this.name = name;
		this.cateogoryApplyTo = cateogoryApplyTo;
		this.expense = expense;
	}

}