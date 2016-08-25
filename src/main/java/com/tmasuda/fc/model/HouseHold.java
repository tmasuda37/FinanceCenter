package com.tmasuda.fc.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class HouseHold {

	@Id
	@Column
	public String houseHoldId;

	@OneToMany(mappedBy = "houseHold")
	public Set<Account> accounts = new HashSet<Account>();

	public HouseHold() {
	}

	public HouseHold(String houseHoldId) {
		this.houseHoldId = houseHoldId;
	}

}