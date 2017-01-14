package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.MonthlyCategoryBalance;
import com.tmasuda.fc.model.key.MonthlyCategoryBalanceKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyCategoryBalanceRepo extends JpaRepository<MonthlyCategoryBalance, MonthlyCategoryBalanceKey> {
}
