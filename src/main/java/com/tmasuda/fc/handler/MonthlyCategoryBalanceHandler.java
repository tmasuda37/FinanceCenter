package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.MonthlyCategoryBalanceCtrl;
import com.tmasuda.fc.model.BalanceFilter;
import com.tmasuda.fc.model.MonthlyCategoryBalance;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/monthly-category-balance")
@Controller
public class MonthlyCategoryBalanceHandler {

    private static final Logger _logger = Logger.getLogger(MonthlyCategoryBalanceHandler.class);

    @Autowired
    private MonthlyCategoryBalanceCtrl monthlyCategoryBalanceCtrl;

    @RequestMapping(value = "/list-balances", method = RequestMethod.POST)
    @ResponseBody
    public List<MonthlyCategoryBalance> listBalances(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody BalanceFilter balanceFilter) {
        return null;
    }
}
