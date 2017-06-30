package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CurrencyCtrl;
import com.tmasuda.fc.ctrl.EventBalanceCtrl;
import com.tmasuda.fc.ctrl.EventCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Event;
import com.tmasuda.fc.model.EventBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/event-balance")
@Controller
public class EventBalanceHandler {

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private EventCtrl eventCtrl;

    @Autowired
    private CurrencyCtrl currencyCtrl;

    @Autowired
    private EventBalanceCtrl eventBalanceCtrl;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public List<EventBalance> findBalanceListByEvent(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody Event event) throws Exception {
        Account anAccount = accountCtrl.createAccount(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        return eventBalanceCtrl.findBalanceListByEvent(event);
    }

}
