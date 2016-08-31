package com.tmasuda.fc.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CategoryCtrl;
import com.tmasuda.fc.ctrl.TransactionCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.Transaction;

public class SenarioProvider {

	public static final Currency CURRENCY_EUR = Currency.getInstance("EUR");

	public static final Currency CURRENCY_JPY = Currency.getInstance("JPY");

	private static final String CATEGORY_FOOD = "Food";

	public Account anAccount;

	@Autowired
	private AccountCtrl accountCtrl;

	@Autowired
	private TransactionCtrl transactionCtrl;

	@Autowired
	private CategoryCtrl categoryCtrl;

	public Account createNewAccount(String methodName) {
		return accountCtrl.getOrCreateModel(new Account(methodName + "-hh", methodName + "-sns"));
	}

	public Account createNewAccount(String houseHoldId, String snsId) {
		return accountCtrl.getOrCreateModel(new Account(houseHoldId, snsId));
	}

	public Transaction expenseForFoodInJPY(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_FOOD, CURRENCY_JPY);
	}

	public Transaction expenseForFoodInEUR(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_FOOD, CURRENCY_EUR);
	}

	private Transaction createTransactionObj(String methodName, BigDecimal amount, String categoryName, Currency currency) {
		anAccount = createNewAccount(methodName);

		Transaction aTransaction = new Transaction();
		aTransaction.account = anAccount;
		aTransaction.amount = amount;
		aTransaction.category = categoryCtrl.getOrCreateModel(new Category(aTransaction.account.houseHold, categoryName));
		aTransaction.currency = currency;
		aTransaction.calendar = Calendar.getInstance();
		aTransaction.description = methodName;
		aTransaction.event = null;
		aTransaction.place = null;

		return transactionCtrl.getOrCreateModel(aTransaction);
	}

}
