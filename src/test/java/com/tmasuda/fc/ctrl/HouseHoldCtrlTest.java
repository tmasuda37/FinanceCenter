package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.util.ScenarioProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class HouseHoldCtrlTest extends ScenarioProvider {

    @Autowired
    private HouseHoldCtrl houseHoldCtrl;

    @Test
    public void createNewHouseHold() {
        String houseHoldId = "createNewHouseHold";
        createNewAccount(houseHoldId, houseHoldId);

        HouseHold existing = houseHoldCtrl.findHouseHoldByHouseHoldId(houseHoldId);
        assertThat(existing, is(notNullValue(HouseHold.class)));
    }

}
