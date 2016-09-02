package com.tmasuda.fc.model.key;

import java.io.Serializable;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.CategoryApplyTo;

@Embeddable
public class AccountBalanceKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	public Account account;

	@Column
	public Currency currency;

	@Column
	@Enumerated(EnumType.STRING)
	public CategoryApplyTo cateogoryApplyTo;

	public AccountBalanceKey() {
	}

	public AccountBalanceKey(Account account, Currency currency, CategoryApplyTo cateogoryApplyTo) {
		this.account = account;
		this.currency = currency;
		this.cateogoryApplyTo = cateogoryApplyTo;
	}

}
