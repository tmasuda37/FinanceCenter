package com.tmasuda.fc.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.tmasuda.fc.model.key.MonthlyCategoryBalanceKey;

@Entity
public class MonthlyCategoryBalance {

	@EmbeddedId
	public MonthlyCategoryBalanceKey aMonthlyCategoryBalanceKey;

	@Column
	public BigDecimal amount = BigDecimal.ZERO;

	public MonthlyCategoryBalance() {
		super();
	}

	public MonthlyCategoryBalance(MonthlyCategoryBalanceKey aMonthlyCategoryBalanceKey) {
		super();
		this.aMonthlyCategoryBalanceKey = aMonthlyCategoryBalanceKey;
	}

}
