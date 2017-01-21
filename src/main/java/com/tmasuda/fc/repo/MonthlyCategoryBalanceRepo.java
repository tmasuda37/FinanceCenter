package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.MonthlyCategoryBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Currency;
import java.util.List;

public interface MonthlyCategoryBalanceRepo extends JpaRepository<MonthlyCategoryBalance, Long> {

    MonthlyCategoryBalance findOneByAccountAndCurrencyAndCategoryAndYearAndMonth(Account account, Currency currency, Category category, int year, int month);

    List<MonthlyCategoryBalance> findAllByAccountAndCurrencyAndYearAndMonth(Account account, Currency currency, int year, int month);

}
