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
public class BalanceKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	public Account account;

	@Column
	@Enumerated(EnumType.STRING)
	public CategoryApplyTo cateogoryApplyTo;

	@Column
	public Currency currency;

	public BalanceKey() {
	}

	public BalanceKey(Account account, CategoryApplyTo cateogoryApplyTo, Currency currency) {
		super();
		this.account = account;
		this.cateogoryApplyTo = cateogoryApplyTo;
		this.currency = currency;
	}

}
