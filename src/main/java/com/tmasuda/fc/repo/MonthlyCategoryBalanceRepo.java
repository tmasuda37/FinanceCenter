package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.MonthlyCategoryBalance;
import com.tmasuda.fc.model.key.MonthlyCategoryBalanceKey;

public interface MonthlyCategoryBalanceRepo extends JpaRepository<MonthlyCategoryBalance, MonthlyCategoryBalanceKey> {
}
