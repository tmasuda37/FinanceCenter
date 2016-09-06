package com.tmasuda.fc.handler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestAttributes;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.util.SenarioProvider;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class AccountHandlerTest extends SenarioProvider {

	@Mock
	private RequestAttributes attrs;

	@Autowired
	private AccountHandler accountHandler;

	@Autowired
	private AccountCtrl accountCtrl;

	@Test
	public void createAccountWithSnsIdOnly() throws Exception {
		String snsId = "createAccountWithSnsIdOnly";
		accountHandler.create(snsId);

		Account existing = accountCtrl.findAccountBySnsId(snsId);
		assertThat(existing, is(notNullValue(Account.class)));
	}

}
