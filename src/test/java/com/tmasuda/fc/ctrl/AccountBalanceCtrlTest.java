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
import com.tmasuda.fc.model.key.BaseAccountBalanceKey;
import com.tmasuda.fc.repo.AccountBalanceRepo;
import com.tmasuda.fc.util.SenarioProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountBalanceCtrlTest extends SenarioProvider {

	@Autowired
	AccountBalanceCtrl accountBalanceCtrl;

	@Autowired
	AccountBalanceRepo accountBalanceRepo;

	@Test
	public void singleExpenseInEUR() {
		expenseForFoodInEUR("singleExpenseInEUR", new BigDecimal(10));

		AccountBalance anAccountBalance = accountBalanceRepo.findOne(new BaseAccountBalanceKey(super.anAccount, CURRENCY_EUR, CategoryApplyTo.Wallet));
		assertThat(anAccountBalance.amount.intValue(), is(-10));
		assertThat(anAccountBalance.baseAccountBalanceKey.currency, is(CURRENCY_EUR));
	}

	@Test
	public void multipleExpensesInEUR() {
		expenseForFoodInEUR("multipleExpensesInEUR", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR", new BigDecimal(10));

		AccountBalance anAccountBalance = accountBalanceRepo.findOne(new BaseAccountBalanceKey(super.anAccount, CURRENCY_EUR, CategoryApplyTo.Wallet));
		assertThat(anAccountBalance.amount.intValue(), is(-50));
		assertThat(anAccountBalance.baseAccountBalanceKey.currency, is(CURRENCY_EUR));
	}

}
