package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccountCtrl {

    @Autowired
    private HouseHoldCtrl houseHoldCtrl;

    @Autowired
    private AccountRepo accountRepo;

    public Account findAccountBySnsId(String snsId) {
        return accountRepo.findOneBySnsId(snsId);
    }

    public Account createAccount(String snsId) {
        Long tempHouseHoldId = System.currentTimeMillis();
        return this.createAccountWithHouseHold(snsId, tempHouseHoldId.toString());
    }

    public Account createAccountWithHouseHold(String snsId, String houseHoldId) {
        Account existing = this.findAccountBySnsId(snsId);
        if (existing != null) {
            return existing;
        }

        Account newAccount = new Account();
        newAccount.snsId = snsId;
        newAccount.houseHold = houseHoldCtrl.createHouseHold(houseHoldId);
        return accountRepo.save(newAccount);
    }

}
