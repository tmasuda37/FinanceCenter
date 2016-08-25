package com.tmasuda.fc.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.tmasuda.fc.model.key.BaseAccountBalanceKey;

@MappedSuperclass
public class BaseAccountBalance {

	@EmbeddedId
	public BaseAccountBalanceKey baseAccountBalanceKey;

	@Column
	public BigDecimal amount = BigDecimal.ZERO;

	public BaseAccountBalance() {
	}
}
