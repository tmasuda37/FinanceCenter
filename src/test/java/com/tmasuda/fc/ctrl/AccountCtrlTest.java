package com.tmasuda.fc.ctrl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.AccountRepo;
import com.tmasuda.fc.repo.HouseHoldRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountCtrlTest {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private HouseHoldRepo houseHoldRepo;

	@Autowired
	private AccountCtrl accountCtrl;

	@Test
	public void createNewAccount() {
		Account aNewAccount = accountCtrl.createNewAccount("facebook id");

		Account savedAccount = accountRepo.findOne(aNewAccount.publicID);
		assertEquals(aNewAccount.publicID, savedAccount.publicID);
	}

	@Test
	public void createNewHomeByCreatingAccount() {
		accountCtrl.createNewAccount("facebook id");

		List<HouseHold> houseHolds = houseHoldRepo.findAll();
		assertEquals(1, houseHolds.size());
	}

}
