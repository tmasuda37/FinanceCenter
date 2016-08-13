package com.tmasuda.fc.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Wallet {
	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long publicID;

	@Column(name = "balance")
	private BigDecimal balance;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(nullable = true)
	private List<Transaction> transactions;
}