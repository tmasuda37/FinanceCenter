package com.tmasuda.fc.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CategoryCtrl;
import com.tmasuda.fc.ctrl.TransactionCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Transaction;

public class SenarioProvider {

	public static final Currency CURRENCY_EUR = Currency.getInstance("EUR");

	public static final Currency CURRENCY_JPY = Currency.getInstance("JPY");

	public static final Currency CURRENCY_USD = Currency.getInstance("USD");

	public static final String CATEGORY_FOOD = "Food";

	public static final String CATEGORY_MEDICAL = "Medical";

	public static final String CATEGORY_INCOME = "Income";

	public Account account;

	@Autowired
	private AccountCtrl accountCtrl;

	@Autowired
	private TransactionCtrl transactionCtrl;

	@Autowired
	private CategoryCtrl categoryCtrl;

	public Account createNewAccount(String methodName) {
		return accountCtrl.createAccount(methodName);
	}

	public Account createNewAccount(String houseHoldId, String snsId) {
		return accountCtrl.createAccountWithHouseHold(snsId, houseHoldId);
	}

	public Transaction incomesForIncomeInEUR(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_INCOME, CURRENCY_EUR);
	}

	public Transaction incomesForIncomeInJPY(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_INCOME, CURRENCY_JPY);
	}

	public Transaction incomesForIncomeInUSD(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_INCOME, CURRENCY_USD);
	}

	public Transaction expenseForFoodInEUR(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_FOOD, CURRENCY_EUR);
	}

	public Transaction expenseForFoodInJPY(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_FOOD, CURRENCY_JPY);
	}

	public Transaction expenseForFoodInUSD(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_FOOD, CURRENCY_USD);
	}

	public Transaction expenseForMedicalInEUR(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_MEDICAL, CURRENCY_EUR);
	}

	public Transaction expenseForMedicalInJPY(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_MEDICAL, CURRENCY_JPY);
	}

	public Transaction expenseForMedicalInUSD(String methodName, BigDecimal amount) {
		return createTransactionObj(methodName, amount, CATEGORY_MEDICAL, CURRENCY_USD);
	}

	private Transaction createTransactionObj(String methodName, BigDecimal amount, String categoryName, Currency currency) {
		account = createNewAccount(methodName);

		Transaction aTransaction = new Transaction();
		aTransaction.account = account;
		aTransaction.amount = amount;

		if (categoryName == CATEGORY_FOOD || categoryName == CATEGORY_MEDICAL) {
			aTransaction.category = categoryCtrl.createWalletExpenseCategory(account.houseHold, categoryName);
		} else if (categoryName == CATEGORY_INCOME) {
			aTransaction.category = categoryCtrl.createWalletIncomeCategory(account.houseHold, categoryName);
		}

		aTransaction.currency = currency;
		aTransaction.calendar = Calendar.getInstance();
		aTransaction.description = methodName;
		aTransaction.event = null;
		aTransaction.place = null;

		return transactionCtrl.createTransaction(aTransaction);
	}

}
