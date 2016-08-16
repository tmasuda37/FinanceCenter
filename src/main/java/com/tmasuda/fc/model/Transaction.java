package com.tmasuda.fc.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long publicID;

	@ManyToOne
	@JoinColumn(name = "account_id")
	public Account account;

	@Column
	public Date date;

	@Column
	public BigDecimal amount;

	@Column
	public Currency currency;

	@ManyToOne
	@JoinColumn(nullable = false)
	public Category category;

	@ManyToOne
	public Place place;

	@ManyToOne
	public Event event;

	@Column
	public String description;
}