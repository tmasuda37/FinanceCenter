package com.tmasuda.fc.ctrl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Balance;
import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.CategoryApplyTo;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.model.key.BalanceKey;
import com.tmasuda.fc.repo.AccountRepo;
import com.tmasuda.fc.repo.BalanceRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransactionCtrlTest {

	@Autowired
	private AccountCtrl accountCtrl;

	@Autowired
	private TransactionCtrl transactionCtrl;

	@Autowired
	private CategoryCtrl categoryCtrl;

	@Autowired
	private BalanceRepo balanceRepo;

	@Autowired
	private AccountRepo accountRepo;

	@Test
	public void createNewExpenseTransaction() {
		Account anAccount = accountCtrl.createNewAccount("facebook id");
		Category exCat = categoryCtrl.createNewCategory(anAccount.household, CategoryApplyTo.Wallet, "Food", true);

		transactionCtrl.createNewTransaction(createTransactionObj(anAccount, new BigDecimal(10.00), exCat));

		BalanceKey balanceKey = new BalanceKey(anAccount, CategoryApplyTo.Wallet, Currency.getInstance("EUR"));
		Balance aBalance = balanceRepo.findOne(balanceKey);
		assertEquals(-10, aBalance.amount.intValue());
	}

	@Test
	public void createNewIncomeTransaction() {
		Account anAccount = accountCtrl.createNewAccount("facebook id");
		Category inCat = categoryCtrl.createNewCategory(anAccount.household, CategoryApplyTo.Wallet, "Food", false);

		transactionCtrl.createNewTransaction(createTransactionObj(anAccount, new BigDecimal(10.00), inCat));

		BalanceKey balanceKey = new BalanceKey(anAccount, CategoryApplyTo.Wallet, Currency.getInstance("EUR"));
		Balance aBalance = balanceRepo.findOne(balanceKey);
		assertEquals(10, aBalance.amount.intValue());
	}

	@Test
	public void createNewExpenseTransactions() {
		Account anAccount = accountCtrl.createNewAccount("facebook id");
		Category exCat = categoryCtrl.createNewCategory(anAccount.household, CategoryApplyTo.Wallet, "Food", true);

		transactionCtrl.createNewTransaction(createTransactionObj(anAccount, new BigDecimal(10.00), exCat));
		transactionCtrl.createNewTransaction(createTransactionObj(anAccount, new BigDecimal(10.00), exCat));

		BalanceKey balanceKey = new BalanceKey(anAccount, CategoryApplyTo.Wallet, Currency.getInstance("EUR"));
		Balance aBalance = balanceRepo.findOne(balanceKey);
		assertEquals(-20, aBalance.amount.intValue());
	}

	@Test
	public void createNewIncomeTransactions() {
		Account anAccount = accountCtrl.createNewAccount("facebook id");
		Category inCat = categoryCtrl.createNewCategory(anAccount.household, CategoryApplyTo.Wallet, "Salary", false);

		transactionCtrl.createNewTransaction(createTransactionObj(anAccount, new BigDecimal(10.00), inCat));
		transactionCtrl.createNewTransaction(createTransactionObj(anAccount, new BigDecimal(10.00), inCat));

		BalanceKey balanceKey = new BalanceKey(anAccount, CategoryApplyTo.Wallet, Currency.getInstance("EUR"));
		Balance aBalance = balanceRepo.findOne(balanceKey);
		assertEquals(20, aBalance.amount.intValue());
	}

	@Test
	public void createNewMixTransactions() {
		Account anAccount = accountCtrl.createNewAccount("facebook id");
		Category exCat = categoryCtrl.createNewCategory(anAccount.household, CategoryApplyTo.Wallet, "Food", true);
		Category inCat = categoryCtrl.createNewCategory(anAccount.household, CategoryApplyTo.Wallet, "Salary", false);

		transactionCtrl.createNewTransaction(createTransactionObj(anAccount, new BigDecimal(10.00), exCat));
		transactionCtrl.createNewTransaction(createTransactionObj(anAccount, new BigDecimal(10.00), inCat));

		BalanceKey balanceKey = new BalanceKey(anAccount, CategoryApplyTo.Wallet, Currency.getInstance("EUR"));
		Balance aBalance = balanceRepo.findOne(balanceKey);
		assertEquals(0, aBalance.amount.intValue());
	}

	@Test
	public void createNewMixCurrencyTransactions() {
		Account anAccount = accountCtrl.createNewAccount("facebook id");
		Category exCat = categoryCtrl.createNewCategory(anAccount.household, CategoryApplyTo.Wallet, "Food", true);
		Category inCat = categoryCtrl.createNewCategory(anAccount.household, CategoryApplyTo.Wallet, "Salary", false);

		transactionCtrl.createNewTransaction(
				createTransactionObj(anAccount, new BigDecimal(100.00), exCat, Currency.getInstance("JPY")));
		transactionCtrl.createNewTransaction(
				createTransactionObj(anAccount, new BigDecimal(100.00), exCat, Currency.getInstance("JPY")));
		transactionCtrl.createNewTransaction(
				createTransactionObj(anAccount, new BigDecimal(100.00), exCat, Currency.getInstance("JPY")));
		transactionCtrl.createNewTransaction(
				createTransactionObj(anAccount, new BigDecimal(200.00), exCat, Currency.getInstance("USD")));

		transactionCtrl.createNewTransaction(
				createTransactionObj(anAccount, new BigDecimal(1000.00), inCat, Currency.getInstance("JPY")));
		transactionCtrl.createNewTransaction(
				createTransactionObj(anAccount, new BigDecimal(1000.00), inCat, Currency.getInstance("JPY")));
		transactionCtrl.createNewTransaction(
				createTransactionObj(anAccount, new BigDecimal(1000.00), inCat, Currency.getInstance("JPY")));
		transactionCtrl.createNewTransaction(
				createTransactionObj(anAccount, new BigDecimal(2000.00), inCat, Currency.getInstance("USD")));

		BalanceKey balanceKey1 = new BalanceKey(anAccount, CategoryApplyTo.Wallet, Currency.getInstance("JPY"));
		Balance aBalance1 = balanceRepo.findOne(balanceKey1);
		assertEquals(2700, aBalance1.amount.intValue());

		BalanceKey balanceKey2 = new BalanceKey(anAccount, CategoryApplyTo.Wallet, Currency.getInstance("USD"));
		Balance aBalance2 = balanceRepo.findOne(balanceKey2);
		assertEquals(1800, aBalance2.amount.intValue());

		Account savedAccount = accountRepo.findOne(anAccount.publicID);
		assertEquals(8, savedAccount.listTransactions.size());
	}

	private Transaction createTransactionObj(Account account, BigDecimal amount, Category category) {
		return createTransactionObj(account, amount, category, Currency.getInstance("EUR"));
	}

	private Transaction createTransactionObj(Account account, BigDecimal amount, Category category, Currency currency) {
		Transaction aTransaction = new Transaction();
		aTransaction.account = account;
		aTransaction.amount = amount;
		aTransaction.category = category;
		aTransaction.currency = currency;
		aTransaction.date = new Date();
		aTransaction.description = "unit testing";
		aTransaction.event = null;
		aTransaction.place = null;
		return aTransaction;
	}
}
