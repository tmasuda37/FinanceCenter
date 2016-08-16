package com.tmasuda.fc.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.tmasuda.fc.model.key.BalanceKey;

@Entity
public class Balance {

	@EmbeddedId
	private BalanceKey balanceKey;

	@Column
	public BigDecimal amount = BigDecimal.ZERO;

	public Balance() {

	}

	public Balance(BalanceKey balanceKey) {
		super();
		this.balanceKey = balanceKey;
	}

}
