package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.AccountRepo;

@Controller
public class AccountCtrl extends AbstractCtrl<Account> {

	@Autowired
	private HouseHoldCtrl houseHoldCtrl;

	@Autowired
	private AccountRepo accountRepo;

	@Override
	public Account getSavedModel(Account instantiated) {
		Example<Account> example = Example.of(instantiated);
		return accountRepo.findOne(example);
	}

	@Override
	public Account createNewModel(Account instantiated) {
		return accountRepo.save(instantiated);
	}

	private HouseHold getInstance(String houseHoldId) {
		return new HouseHold(houseHoldId);
	}

	@Override
	public void preRun(Account instantiated) {
		instantiated.houseHold = houseHoldCtrl.getOrCreateModel(getInstance(instantiated.houseHoldId));
	}

	@Override
	public void postRun(Account committed) {
	}

}
