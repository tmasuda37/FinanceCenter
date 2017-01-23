package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountBalanceCtrl;
import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.MonthlyCategoryBalanceCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.BalanceFilter;
import com.tmasuda.fc.model.MonthlyCategoryBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/summary")
@Controller
public class SummaryHandler {

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

    @RequestMapping(value = "/monthly-house-hold-balance", method = RequestMethod.POST)
    @ResponseBody
    public List<MonthlyCategoryBalance> getMonthlyHouseHoldCategoryBalance(
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

}
