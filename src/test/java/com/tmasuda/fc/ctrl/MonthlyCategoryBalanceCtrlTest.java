package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.repo.MonthlyCategoryBalanceRepo;
import com.tmasuda.fc.util.ScenarioProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class MonthlyCategoryBalanceCtrlTest extends ScenarioProvider {

    @Autowired
    CategoryCtrl categoryCtrl;

    @Autowired
    MonthlyCategoryBalanceCtrl monthlyCategoryBalanceCtrl;

    @Autowired
    MonthlyCategoryBalanceRepo monthlyCategoryBalanceRepo;

    @Test
    public void newTest() {
    }

}
