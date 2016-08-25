package com.tmasuda.fc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

@Entity
public class Account {

	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long publicId;

	@NaturalId
	@Column
	public String snsId;

	@ManyToOne
	@JoinColumn(name = "house_hold_id", nullable = true)
	public HouseHold houseHold;

	@OneToMany(mappedBy = "account")
	public Set<Transaction> transactions = new HashSet<Transaction>();

	@Transient
	public String houseHoldId;

	public Account() {
	}

	public Account(String snsId) {
		this.snsId = snsId;
	}

	public Account(String houseHoldId, String snsId) {
		this.houseHoldId = houseHoldId;
		this.snsId = snsId;
	}

}