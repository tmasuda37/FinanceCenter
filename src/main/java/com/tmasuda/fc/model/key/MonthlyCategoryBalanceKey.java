package com.tmasuda.fc.model.key;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Category;

@Embeddable
public class MonthlyCategoryBalanceKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	public Account account;

	@Column
	public Currency currency;

	@ManyToOne
	public Category category;

	@Column
	public int year;

	@Column
	public int month;

	public MonthlyCategoryBalanceKey() {
	}

	public MonthlyCategoryBalanceKey(Account account, Currency currency, Category category, Calendar calendar) {
		super();
		this.account = account;
		this.currency = currency;
		this.category = category;
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
	}

}
