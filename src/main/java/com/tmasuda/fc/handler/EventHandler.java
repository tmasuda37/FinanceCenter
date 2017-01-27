package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.EventCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/event")
@Controller
public class EventHandler {

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private EventCtrl eventCtrl;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Event> getList(@RequestAttribute(value = "SNS_ID") String snsId) {
        Account anAccount = accountCtrl.createAccount(snsId);
        return eventCtrl.findEventsByHouseHold(anAccount.houseHold);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public List<Event> create(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody @Valid Event event) throws Exception {
        Account anAccount = accountCtrl.createAccount(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        event.houseHold = anAccount.houseHold;

        eventCtrl.createOrSaveEvent(event);

        return eventCtrl.findEventsByHouseHold(anAccount.houseHold);
    }

}
