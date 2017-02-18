package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.*;
import com.tmasuda.fc.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/summary")
@Controller
public class SummaryHandler {

    private static final Logger _logger = Logger.getLogger(SummaryHandler.class);

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private AccountBalanceCtrl accountBalanceCtrl;

    @Autowired
    private CurrencyCtrl currencyCtrl;

    @Autowired
    private CategoryCtrl categoryCtrl;

    @Autowired
    private MonthlyCategoryBalanceCtrl monthlyCategoryBalanceCtrl;

    @RequestMapping(value = "/balance", method = RequestMethod.POST)
    @ResponseBody
    public List<AccountBalance> getBalance(
            @RequestAttribute(value = "SNS_ID") String snsId,
            @RequestBody BalanceFilter balanceFilter) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        return accountBalanceCtrl.getBalance(anAccount, balanceFilter.currency);
    }

    @RequestMapping(value = "/monthly-account-balance", method = RequestMethod.POST)
    @ResponseBody
    public List<MonthlyCategoryBalance> getMonthlyAccountCategoryBalance(
            @RequestAttribute(value = "SNS_ID") String snsId,
            @RequestBody BalanceFilter balanceFilter) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        return monthlyCategoryBalanceCtrl.getMonthlyBalance(
                anAccount,
                balanceFilter.currency,
                balanceFilter.calendar);
    }

    @RequestMapping(value = "/monthly-house-hold-balance", method = RequestMethod.POST)
    @ResponseBody
    public Collection<MonthlyCategoryBalance> getMonthlyHouseHoldCategoryBalance(
            @RequestAttribute(value = "SNS_ID") String snsId,
            @RequestBody BalanceFilter balanceFilter) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        HouseHold aHouseHold = anAccount.houseHold;

        HashMap<Long, MonthlyCategoryBalance> monthlyCategoryBalanceList = new HashMap<>();
        aHouseHold.accounts.forEach(account -> {
            List<MonthlyCategoryBalance> newList = monthlyCategoryBalanceCtrl.getMonthlyBalance(account, balanceFilter.currency, balanceFilter.calendar);
            _logger.info("SummaryHandler#getMonthlyHouseHoldCategoryBalance - newList is: " + newList.size());
            newList.forEach(item -> {
                if (monthlyCategoryBalanceList.containsKey(item.category.publicId)) {
                    MonthlyCategoryBalance exItem = monthlyCategoryBalanceList.get(item.category.publicId);
                    item.amount = item.amount.add(exItem.amount);
                    monthlyCategoryBalanceList.put(item.category.publicId, item);
                } else {
                    monthlyCategoryBalanceList.put(item.category.publicId, item);
                }
            });
        });

        return monthlyCategoryBalanceList.values();
    }

    @RequestMapping(value = "/set-budget", method = RequestMethod.POST)
    @ResponseBody
    public void setBudget(
            @RequestAttribute(value = "SNS_ID") String snsId,
            @RequestBody Calendar calendar) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        List<Category> trackingCategories = categoryCtrl.findCategoriesByHouseHold(anAccount.houseHold);

        trackingCategories.forEach(trackingCategory -> {
            currencyCtrl.getList().forEach(currency -> {

                if (!monthlyCategoryBalanceCtrl.hasMonthlyCategoryBalanceByUniqueKeys(
                        anAccount,
                        currency,
                        trackingCategory,
                        year,
                        month)) {

                    MonthlyCategoryBalance monthlyCategoryBalance = new MonthlyCategoryBalance(
                            anAccount,
                            currency,
                            trackingCategory,
                            year,
                            month);

                    monthlyCategoryBalanceCtrl.save(monthlyCategoryBalance);
                }

            });
        });
    }

    @RequestMapping(value = "/update-budget", method = RequestMethod.POST)
    @ResponseBody
    public void updateBudget(
            @RequestAttribute(value = "SNS_ID") String snsId,
            @RequestBody List<MonthlyCategoryBalance> updatedBudgets) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        updatedBudgets.forEach(updateBudget -> {
            MonthlyCategoryBalance monthlyCategoryBalance = monthlyCategoryBalanceCtrl.findOneByPublicId(updateBudget.publicId);
            monthlyCategoryBalance.budget = updateBudget.budget;
            monthlyCategoryBalanceCtrl.updateBudget(monthlyCategoryBalance);
        });
    }

    @RequestMapping(value = "/get-budget-total", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal getBudgetTotal(
            @RequestAttribute(value = "SNS_ID") String snsId,
            @RequestBody MonthlyCategoryBalance requestBudget) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        return monthlyCategoryBalanceCtrl.getBudgetTotal(anAccount, requestBudget.currency, requestBudget.calendar, requestBudget.isBudgetTracking);
    }

}
