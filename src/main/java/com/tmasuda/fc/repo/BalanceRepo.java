package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.Balance;
import com.tmasuda.fc.model.key.BalanceKey;

public interface BalanceRepo extends JpaRepository<Balance, BalanceKey> {
}
