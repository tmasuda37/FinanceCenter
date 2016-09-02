package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.key.AccountBalanceKey;

public interface AccountBalanceRepo extends JpaRepository<AccountBalance, AccountBalanceKey> {
}
