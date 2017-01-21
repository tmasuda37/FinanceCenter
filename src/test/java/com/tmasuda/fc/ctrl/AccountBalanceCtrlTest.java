package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.repo.AccountBalanceRepo;
import com.tmasuda.fc.repo.CategoryRepo;
import com.tmasuda.fc.util.SenarioProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class AccountBalanceCtrlTest extends SenarioProvider {

    @Autowired
    AccountBalanceCtrl accountBalanceCtrl;

    @Autowired
    AccountBalanceRepo accountBalanceRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Test
    public void newTest() {
    }

}
