package com.tmasuda.fc.ctrl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.repo.AccountRepo;
import com.tmasuda.fc.util.SenarioProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountCtrlTest extends SenarioProvider {

	@Autowired
	private AccountRepo accountRepo;

	@Test
	public void createNewAccount() {
		createNewAccount("createNewAccount");

		Account searchAccount = new Account("createNewAccount-sns");
		Example<Account> example = Example.of(searchAccount);
		assertNotNull(accountRepo.findOne(example));
	}

}
