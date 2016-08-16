package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Home;
import com.tmasuda.fc.repo.AccountRepo;
import com.tmasuda.fc.repo.HomeRepo;

@Controller
public class AccountCtrl {

	@Autowired
	private HomeRepo homeRepo;

	@Autowired
	private AccountRepo accountRepo;

	public Account createNewAccount(String snsID) {
		Home home = createNewHome();

		Account anAccount = new Account();
		anAccount.snsID = snsID;
		anAccount.home = home;

		return accountRepo.save(anAccount);
	}

	private Home createNewHome() {
		Home aHome = new Home();
		return homeRepo.save(aHome);
	}

}
