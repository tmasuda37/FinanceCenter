package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountBalanceCtrl;
import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CategoryCtrl;
import com.tmasuda.fc.ctrl.CurrencyCtrl;
import com.tmasuda.fc.model.MonthlyCategoryBalance;
import com.tmasuda.fc.util.ScenarioProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MonthlyCategoryBalanceHandlerTest extends ScenarioProvider {

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private AccountBalanceCtrl accountBalanceCtrl;

    @Autowired
    private CurrencyCtrl currencyCtrl;

    @Autowired
    private CategoryCtrl categoryCtrl;

    @Autowired
    private MonthlyCategoryBalanceHandler monthlyCategoryBalanceHandler;

    @Test
    public void testListingEmptyBalancesForAccount() throws Exception {
        String snsId = "testListingBalancesForAccount";

        List<MonthlyCategoryBalance> list = monthlyCategoryBalanceHandler.listBalances(snsId, null);
    }

}
