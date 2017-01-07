package com.tmasuda.fc.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transaction {

	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long publicId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "account_id")
	public Account account;

	@Column
	public Calendar calendar;

	@Column
	@NotNull(message = "Amount cannot be null or empty.")
	public BigDecimal amount;

	@Column
	public Currency currency;

	@ManyToOne(optional = false)
	public Category category;

	@ManyToOne
	public Place place;

	@ManyToOne
	public Event event;

	@Column
	public String description;

}