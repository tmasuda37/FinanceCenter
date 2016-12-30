package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CategoryCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/category")
@Controller
public class CategoryHandler {

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private CategoryCtrl categoryCtrl;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> getList(@RequestAttribute(value = "SNS_ID") String snsId) {
        Account anAccount = accountCtrl.createAccount(snsId);
        return categoryCtrl.findCategoriesByHouseHold(anAccount.houseHold);
    }

}
