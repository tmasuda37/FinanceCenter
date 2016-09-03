package com.tmasuda.fc.ctrl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.MonthlyCategoryBalance;
import com.tmasuda.fc.model.key.MonthlyCategoryBalanceKey;
import com.tmasuda.fc.repo.MonthlyCategoryBalanceRepo;
import com.tmasuda.fc.util.SenarioProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class MonthlyCategoryBalanceCtrlTest extends SenarioProvider {

	@Autowired
	CategoryCtrl categoryCtrl;

	@Autowired
	MonthlyCategoryBalanceCtrl monthlyCategoryBalanceCtrl;

	@Autowired
	MonthlyCategoryBalanceRepo monthlyCategoryBalanceRepo;

	private Calendar calendar = Calendar.getInstance();

	@Test
	public void singleExpenseInEUR_ForMonthlyCategoryBalance() {
		expenseForFoodInEUR("singleExpenseInEUR_ForMonthlyCategoryBalance", new BigDecimal(10));

		MonthlyCategoryBalance aMonthlyCategoryBalance = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_EUR, getOrCreateCategory(CATEGORY_FOOD, true), calendar));
		assertThat(aMonthlyCategoryBalance.amount.doubleValue(), is(-10.0));
		assertThat(aMonthlyCategoryBalance.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_EUR));
	}

	@Test
	public void multipleExpensesInEUR_ForMonthlyCategoryBalance() {
		expenseForFoodInEUR("multipleExpensesInEUR_ForMonthlyCategoryBalance", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesInEUR_ForMonthlyCategoryBalance", new BigDecimal(10));
		expenseForMedicalInEUR("multipleExpensesInEUR_ForMonthlyCategoryBalance", new BigDecimal(10));
		expenseForMedicalInEUR("multipleExpensesInEUR_ForMonthlyCategoryBalance", new BigDecimal(10));
		expenseForMedicalInEUR("multipleExpensesInEUR_ForMonthlyCategoryBalance", new BigDecimal(10));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForFoodInEUR = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_EUR, getOrCreateCategory(CATEGORY_FOOD, true), calendar));
		assertThat(aMonthlyCategoryBalanceForFoodInEUR.amount.doubleValue(), is(-20.0));
		assertThat(aMonthlyCategoryBalanceForFoodInEUR.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_EUR));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForMedicalInEUR = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_EUR, getOrCreateCategory(CATEGORY_MEDICAL, true), calendar));
		assertThat(aMonthlyCategoryBalanceForMedicalInEUR.amount.doubleValue(), is(-30.0));
		assertThat(aMonthlyCategoryBalanceForMedicalInEUR.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_EUR));
	}

	@Test
	public void multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance() {
		incomesForIncomeInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(10));
		incomesForIncomeInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(110));
		incomesForIncomeInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(1110));

		expenseForMedicalInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(20));
		expenseForMedicalInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(200));
		expenseForMedicalInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(2));
		expenseForFoodInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(10));
		expenseForFoodInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(100));
		expenseForFoodInEUR("multipleExpensesAndIncomesInEUR_ForMonthlyCategoryBalance", new BigDecimal(1));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForFoodInEUR = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_EUR, getOrCreateCategory(CATEGORY_FOOD, true), calendar));
		assertThat(aMonthlyCategoryBalanceForFoodInEUR.amount.doubleValue(), is(-111.0));
		assertThat(aMonthlyCategoryBalanceForFoodInEUR.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_EUR));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForMedicalInEUR = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_EUR, getOrCreateCategory(CATEGORY_MEDICAL, true), calendar));
		assertThat(aMonthlyCategoryBalanceForMedicalInEUR.amount.doubleValue(), is(-222.0));
		assertThat(aMonthlyCategoryBalanceForMedicalInEUR.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_EUR));
	}

	@Test
	public void multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance() {
		expenseForFoodInEUR("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(1));
		expenseForFoodInEUR("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(10));
		expenseForFoodInJPY("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(2));
		expenseForFoodInJPY("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(20));
		expenseForFoodInUSD("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(3));
		expenseForFoodInUSD("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(30));
		expenseForMedicalInEUR("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(4));
		expenseForMedicalInEUR("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(40));
		expenseForMedicalInJPY("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(5));
		expenseForMedicalInJPY("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(50));
		expenseForMedicalInUSD("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(6));
		expenseForMedicalInUSD("multipleExpensesInEURandJPYandUSD_ForMonthlyCategoryBalance", new BigDecimal(60));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForFoodInEUR = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_EUR, getOrCreateCategory(CATEGORY_FOOD, true), calendar));
		assertThat(aMonthlyCategoryBalanceForFoodInEUR.amount.doubleValue(), is(-11.0));
		assertThat(aMonthlyCategoryBalanceForFoodInEUR.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_EUR));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForFoodInJPY = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_JPY, getOrCreateCategory(CATEGORY_FOOD, true), calendar));
		assertThat(aMonthlyCategoryBalanceForFoodInJPY.amount.doubleValue(), is(-22.0));
		assertThat(aMonthlyCategoryBalanceForFoodInJPY.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_JPY));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForFoodInUSD = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_USD, getOrCreateCategory(CATEGORY_FOOD, true), calendar));
		assertThat(aMonthlyCategoryBalanceForFoodInUSD.amount.doubleValue(), is(-33.0));
		assertThat(aMonthlyCategoryBalanceForFoodInUSD.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_USD));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForMedicalInEUR = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_EUR, getOrCreateCategory(CATEGORY_MEDICAL, true), calendar));
		assertThat(aMonthlyCategoryBalanceForMedicalInEUR.amount.doubleValue(), is(-44.0));
		assertThat(aMonthlyCategoryBalanceForMedicalInEUR.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_EUR));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForMedicalInJPY = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_JPY, getOrCreateCategory(CATEGORY_MEDICAL, true), calendar));
		assertThat(aMonthlyCategoryBalanceForMedicalInJPY.amount.doubleValue(), is(-55.0));
		assertThat(aMonthlyCategoryBalanceForMedicalInJPY.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_JPY));

		MonthlyCategoryBalance aMonthlyCategoryBalanceForMedicalInUSD = monthlyCategoryBalanceRepo.findOne(new MonthlyCategoryBalanceKey(super.anAccount, CURRENCY_USD, getOrCreateCategory(CATEGORY_MEDICAL, true), calendar));
		assertThat(aMonthlyCategoryBalanceForMedicalInUSD.amount.doubleValue(), is(-66.0));
		assertThat(aMonthlyCategoryBalanceForMedicalInUSD.aMonthlyCategoryBalanceKey.currency, is(CURRENCY_USD));
	}

	private Category getOrCreateCategory(String categoryName, boolean toExpense) {
		return categoryCtrl.getOrCreateModel(new Category(super.anAccount.houseHold, categoryName, toExpense));
	}

}
