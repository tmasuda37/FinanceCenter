package com.tmasuda.fc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HouseHold {
	@Id
	@Column(name = "public_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long publicID;
}