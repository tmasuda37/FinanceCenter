package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.AccountRepo;
import com.tmasuda.fc.repo.HouseHoldRepo;

@Controller
public class AccountCtrl {

	@Autowired
	private HouseHoldRepo houseHoldRepo;

	@Autowired
	private AccountRepo accountRepo;

	public Account createNewAccount(String snsID) {
		HouseHold houseHold = createNewHome();

		Account anAccount = new Account();
		anAccount.snsID = snsID;
		anAccount.household = houseHold;

		return accountRepo.save(anAccount);
	}

	private HouseHold createNewHome() {
		HouseHold houseHold = new HouseHold();
		return houseHoldRepo.save(houseHold);
	}

}
