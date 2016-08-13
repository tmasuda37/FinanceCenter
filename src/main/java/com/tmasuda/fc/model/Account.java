package com.tmasuda.fc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account {
	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long publicID;

	@Column(name = "sns_id")
	private String snsID;

	@OneToOne
	private Wallet walletID;

	@OneToOne
	private Bank bankID;
}