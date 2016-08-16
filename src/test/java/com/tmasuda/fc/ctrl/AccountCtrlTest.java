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
import com.tmasuda.fc.model.Home;
import com.tmasuda.fc.repo.AccountRepo;
import com.tmasuda.fc.repo.HomeRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountCtrlTest {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private HomeRepo homeRepo;

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

		List<Home> homes = homeRepo.findAll();
		assertEquals(1, homes.size());
	}

}
