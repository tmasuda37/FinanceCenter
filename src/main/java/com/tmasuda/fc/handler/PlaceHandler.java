package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.PlaceCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/place")
@Controller
public class PlaceHandler {

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private PlaceCtrl placeCtrl;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Place> getList(@RequestAttribute(value = "SNS_ID") String snsId) {
        Account anAccount = accountCtrl.createAccount(snsId);
        return placeCtrl.findPlacesByHouseHold(anAccount.houseHold);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public List<Place> create(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody @Valid Place anPlace) throws Exception {
        Account anAccount = accountCtrl.createAccount(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        anPlace.houseHold = anAccount.houseHold;

        placeCtrl.createOrSavePlace(anPlace);

        return placeCtrl.findPlacesByHouseHold(anAccount.houseHold);
    }

}
