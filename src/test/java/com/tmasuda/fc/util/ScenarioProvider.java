package com.tmasuda.fc.util;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CategoryCtrl;
import com.tmasuda.fc.ctrl.TransactionCtrl;
import com.tmasuda.fc.model.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Currency;

public class ScenarioProvider {

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

}
