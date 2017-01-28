package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountBalanceCtrl;
import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.MonthlyCategoryBalanceCtrl;
import com.tmasuda.fc.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        HashMap<String, MonthlyCategoryBalance> monthlyCategoryBalanceList = new HashMap<>();
        aHouseHold.accounts.forEach(account -> {
            List<MonthlyCategoryBalance> newList = monthlyCategoryBalanceCtrl.getMonthlyBalance(account, balanceFilter.currency, balanceFilter.calendar);
            _logger.info("SummaryHandler#getMonthlyHouseHoldCategoryBalance - newList is: " + newList.size());
            newList.forEach(item -> {
                if (monthlyCategoryBalanceList.containsKey(item.category.name)) {
                    MonthlyCategoryBalance exItem = monthlyCategoryBalanceList.get(item.category.name);
                    item.amount = item.amount.add(exItem.amount);
                    monthlyCategoryBalanceList.put(item.category.name, item);
                } else {
                    monthlyCategoryBalanceList.put(item.category.name, item);
                }
            });
        });

        return monthlyCategoryBalanceList.values();
    }

}
