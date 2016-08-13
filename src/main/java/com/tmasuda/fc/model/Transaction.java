package com.tmasuda.fc.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long publicID;

	@Column
	private Date date;

	@Column
	private BigDecimal txAmount;

	@ManyToOne
	private Currency currencyID;

	@ManyToOne
	private Category categoryID;

	@ManyToOne
	private Place placeID;

	@ManyToOne
	private Event eventID;

	@Column
	private String txDescription;
}