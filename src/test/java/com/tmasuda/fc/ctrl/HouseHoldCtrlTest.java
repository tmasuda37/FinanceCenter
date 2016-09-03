package com.tmasuda.fc.ctrl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.HouseHoldRepo;
import com.tmasuda.fc.util.SenarioProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class HouseHoldCtrlTest extends SenarioProvider {

	@Autowired
	private HouseHoldRepo houseHoldRepo;

	@Test
	public void createNewHouseHold() {
		createNewAccount("createNewHouseHold");

		HouseHold searchAccount = new HouseHold("createNewHouseHold-hh");
		Example<HouseHold> example = Example.of(searchAccount);
		assertNotNull(houseHoldRepo.findOne(example));
	}

	@Test
	public void addAccountToExistingHouseHold() {
		createNewAccount("addAccountToExistingHouseHold-hh", "addAccountToExistingHouseHold1-sns");
		createNewAccount("addAccountToExistingHouseHold-hh", "addAccountToExistingHouseHold2-sns");

		// note: findOne() and getOne() are different each other
		HouseHold aHouseHold = houseHoldRepo.findOne("addAccountToExistingHouseHold-hh");
		assertThat(aHouseHold.accounts.size(), is(2));
	}

	@Test
	public void addAccountToNewHouseHold() {
		createNewAccount("addAccountToNewHouseHold1-hh", "addAccountToNewHouseHold1-sns");
		createNewAccount("addAccountToNewHouseHold2-hh", "addAccountToNewHouseHold2-sns");

		// note: findOne() and getOne() are different each other
		HouseHold aHouseHold = houseHoldRepo.findOne("addAccountToNewHouseHold1-hh");
		assertThat(aHouseHold.accounts.size(), is(1));
	}

}
