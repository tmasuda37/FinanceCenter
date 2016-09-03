package com.tmasuda.fc.ctrl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.CategoryApplyTo;
import com.tmasuda.fc.model.key.AccountBalanceKey;
import com.tmasuda.fc.repo.AccountBalanceRepo;
import com.tmasuda.fc.util.SenarioProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class AccountBalanceCtrlTest extends SenarioProvider {

	@Autowired
	AccountBalanceCtrl accountBalanceCtrl;

	@Autowired
	AccountBalanceRepo accountBalanceRepo;

	@Test
	public void singleExpenseInEUR_ForAccountBalance() {
		expenseForFoodInEUR("singleExpenseInEUR_ForAccountBalance", new BigDecimal(10));

		AccountBalance anAccountBalance = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_EUR, CategoryApplyTo.Wallet));
		assertThat(anAccountBalance.amount.doubleValue(), is(-10.0));
		assertThat(anAccountBalance.anAccountBalanceKey.currency, is(CURRENCY_EUR));
	}

	@Test
	public void multipleExpensesInEUR_ForAccountBalance() {
		expenseForFoodInEUR("multipleExpensesInEUR_ForAccountBalance", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR_ForAccountBalance", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR_ForAccountBalance", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR_ForAccountBalance", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR_ForAccountBalance", new BigDecimal(10));

		AccountBalance anAccountBalance = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_EUR, CategoryApplyTo.Wallet));
		assertThat(anAccountBalance.amount.doubleValue(), is(-50.0));
		assertThat(anAccountBalance.anAccountBalanceKey.currency, is(CURRENCY_EUR));
	}

	@Test
	public void multipleExpensesAndIncomesInEUR_ForAccountBalance() {
		incomesForIncomeInEUR("multipleExpensesAndIncomesInEUR_ForAccountBalance", new BigDecimal(10));
		incomesForIncomeInEUR("multipleExpensesAndIncomesInEUR_ForAccountBalance", new BigDecimal(110));
		incomesForIncomeInEUR("multipleExpensesAndIncomesInEUR_ForAccountBalance", new BigDecimal(1110));

		expenseForFoodInEUR("multipleExpensesAndIncomesInEUR_ForAccountBalance", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesAndIncomesInEUR_ForAccountBalance", new BigDecimal(100));
		expenseForFoodInEUR("multipleExpensesAndIncomesInEUR_ForAccountBalance", new BigDecimal(10));

		AccountBalance anAccountBalance = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_EUR, CategoryApplyTo.Wallet));
		assertThat(anAccountBalance.amount.doubleValue(), is(1110.0));
		assertThat(anAccountBalance.anAccountBalanceKey.currency, is(CURRENCY_EUR));
	}

	@Test
	public void multipleExpensesInEURandJPYandUSD_ForAccountBalance() {
		expenseForFoodInEUR("multipleExpensesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(22));
		expenseForFoodInEUR("multipleExpensesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(21));
		expenseForFoodInJPY("multipleExpensesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(10));
		expenseForFoodInJPY("multipleExpensesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(50));
		expenseForFoodInUSD("multipleExpensesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(30));
		expenseForFoodInUSD("multipleExpensesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(50));

		AccountBalance anAccountBalanceinEUR = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_EUR, CategoryApplyTo.Wallet));
		assertThat(anAccountBalanceinEUR.amount.doubleValue(), is(-43.0));
		assertThat(anAccountBalanceinEUR.anAccountBalanceKey.currency, is(CURRENCY_EUR));

		AccountBalance anAccountBalanceinJPY = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_JPY, CategoryApplyTo.Wallet));
		assertThat(anAccountBalanceinJPY.amount.doubleValue(), is(-60.0));
		assertThat(anAccountBalanceinJPY.anAccountBalanceKey.currency, is(CURRENCY_JPY));

		AccountBalance anAccountBalanceinUSD = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_USD, CategoryApplyTo.Wallet));
		assertThat(anAccountBalanceinUSD.amount.doubleValue(), is(-80.0));
		assertThat(anAccountBalanceinUSD.anAccountBalanceKey.currency, is(CURRENCY_USD));
	}

	@Test
	public void multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance() {
		incomesForIncomeInEUR("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(50));
		incomesForIncomeInJPY("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(10000));
		incomesForIncomeInUSD("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(100));

		expenseForFoodInEUR("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(20));
		expenseForFoodInEUR("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(10));
		expenseForFoodInJPY("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(498));
		expenseForFoodInJPY("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(298));
		expenseForFoodInUSD("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(3.25));
		expenseForFoodInUSD("multipleExpensesAndIncomesInEURandJPYandUSD_ForAccountBalance", new BigDecimal(5.55));

		AccountBalance anAccountBalanceinEUR = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_EUR, CategoryApplyTo.Wallet));
		assertThat(anAccountBalanceinEUR.amount.doubleValue(), is(20.0));
		assertThat(anAccountBalanceinEUR.anAccountBalanceKey.currency, is(CURRENCY_EUR));

		AccountBalance anAccountBalanceinJPY = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_JPY, CategoryApplyTo.Wallet));
		assertThat(anAccountBalanceinJPY.amount.doubleValue(), is(9204.0));
		assertThat(anAccountBalanceinJPY.anAccountBalanceKey.currency, is(CURRENCY_JPY));

		AccountBalance anAccountBalanceinUSD = accountBalanceRepo.findOne(new AccountBalanceKey(super.anAccount, CURRENCY_USD, CategoryApplyTo.Wallet));
		assertThat(anAccountBalanceinUSD.amount.doubleValue(), is(91.20));
		assertThat(anAccountBalanceinUSD.anAccountBalanceKey.currency, is(CURRENCY_USD));
	}
}
