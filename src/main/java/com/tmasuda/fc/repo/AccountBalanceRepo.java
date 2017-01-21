package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.CategoryApplyTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Currency;
import java.util.List;

public interface AccountBalanceRepo extends JpaRepository<AccountBalance, Long> {

    AccountBalance findOneByAccountAndCurrencyAndCategoryApplyTo(Account account, Currency currency, CategoryApplyTo categoryApplyTo);

    List<AccountBalance> findAllByAccountAndCurrency(Account account, Currency currency);

}
