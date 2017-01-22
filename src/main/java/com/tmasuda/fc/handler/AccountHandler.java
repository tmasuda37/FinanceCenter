package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CategoryCtrl;
import com.tmasuda.fc.ctrl.HouseHoldCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.HouseHold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/account")
@Controller
public class AccountHandler {

    @Autowired
    private HouseHoldCtrl houseHoldCtrl;

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private CategoryCtrl categoryCtrl;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Account create(@RequestAttribute(value = "SNS_ID") String snsId) throws Exception {
        return accountCtrl.createAccount(snsId);
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.GET)
    @ResponseBody
    public HouseHold retrieveHouseHold(@RequestAttribute(value = "SNS_ID") String snsId) throws Exception {
        Account account = accountCtrl.createAccount(snsId);

        if (account == null) {
            throw new Exception("Account Error!");
        }

        return account.houseHold;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public HouseHold updateHouseHold(@RequestAttribute(value = "SNS_ID") String snsId, @Valid @RequestBody HouseHold houseHold) throws Exception {
        Account account = accountCtrl.createAccount(snsId);

        if (account == null) {
            throw new Exception("Account Error!");
        }

        account.houseHold = houseHoldCtrl.createHouseHold(houseHold.houseHoldId);

        accountCtrl.updateAccount(account);

        return account.houseHold;
    }

}
