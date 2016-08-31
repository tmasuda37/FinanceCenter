package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.key.BaseAccountBalanceKey;

public interface AccountBalanceRepo extends JpaRepository<AccountBalance, BaseAccountBalanceKey> {
}
