package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CurrencyCtrl;
import com.tmasuda.fc.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Currency;
import java.util.Set;

@RequestMapping("/currency")
@Controller
public class CurrencyHandler {

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private CurrencyCtrl currencyCtrl;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Set<Currency> getList() {
        return currencyCtrl.getList();
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    @ResponseBody
    public Currency getDefaultCurrency(@RequestAttribute(value = "SNS_ID") String snsId) throws Exception {
        Account anAccount = accountCtrl.createAccount(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        return anAccount.defaultCurrency;
    }
}
