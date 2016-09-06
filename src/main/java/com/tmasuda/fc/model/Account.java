package com.tmasuda.fc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@JsonIgnore
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

	public Account() {
	}

}