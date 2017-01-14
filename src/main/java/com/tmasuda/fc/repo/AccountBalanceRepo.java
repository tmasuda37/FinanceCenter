package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.key.AccountBalanceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBalanceRepo extends JpaRepository<AccountBalance, AccountBalanceKey> {
}
