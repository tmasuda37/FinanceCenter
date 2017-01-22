package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.ApplyTo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Currency;
import java.util.List;

public interface AccountBalanceRepo extends JpaRepository<AccountBalance, Long> {

    AccountBalance findOneByAccountAndCurrencyAndApplyTo(Account account, Currency currency, ApplyTo applyTo);

    List<AccountBalance> findAllByAccountAndCurrency(Account account, Currency currency);

}
