package com.tmasuda.fc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {
	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long publicID;

	@Column
	public String snsID;

	@ManyToOne
	public Home home;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public List<Transaction> listTransactions;

}