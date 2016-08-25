package com.tmasuda.fc.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.tmasuda.fc.model.key.BaseAccountBalanceKey;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AccountBalance extends BaseAccountBalance {

	@EmbeddedId
	public BaseAccountBalanceKey baseAccountBalanceKey;

	public AccountBalance() {
		super();
	}

	public AccountBalance(BaseAccountBalanceKey baseAccountBalanceKey) {
		super();
		this.baseAccountBalanceKey = baseAccountBalanceKey;
	}
}
