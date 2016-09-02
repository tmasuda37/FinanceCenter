package com.tmasuda.fc.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.tmasuda.fc.model.key.AccountBalanceKey;

@Entity
public class AccountBalance {

	@EmbeddedId
	public AccountBalanceKey anAccountBalanceKey;

	@Column
	public BigDecimal amount = BigDecimal.ZERO;

	public AccountBalance() {
		super();
	}

	public AccountBalance(AccountBalanceKey anAccountBalanceKey) {
		super();
		this.anAccountBalanceKey = anAccountBalanceKey;
	}
}
