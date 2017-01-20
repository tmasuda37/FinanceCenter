package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.repo.AccountBalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Controller
public class AccountBalanceCtrl {

    @Autowired
    private MonthlyCategoryBalanceCtrl categoryBalanceCtrl;

    @Autowired
    private AccountBalanceRepo accountBalanceRepo;

    @Transactional
    public void updateBalance(Transaction savedTransaction) {
        AccountBalance updatingAccountBalance = getBalance(savedTransaction);
        updatingAccountBalance.amount = calcBalance(updatingAccountBalance, savedTransaction);
        accountBalanceRepo.save(updatingAccountBalance);

        categoryBalanceCtrl.updateBalance(savedTransaction);
    }

    public List<AccountBalance> getBalance(Account anAccount, Currency currency) {
        return accountBalanceRepo.findAllByAccountAndCurrency(
                anAccount,
                currency);
    }

    protected BigDecimal calcBalance(AccountBalance aBalance, Transaction aTransaction) {
        BigDecimal result;

        if (aTransaction.category.toExpense) {
            result = aBalance.amount.subtract(aTransaction.amount);
        } else {
            result = aBalance.amount.add(aTransaction.amount);
        }

        return result;
    }

    public AccountBalance getBalance(Transaction transaction) {
        AccountBalance existing = accountBalanceRepo
                .findOneByAccountAndCurrencyAndCategoryApplyTo(
                        transaction.account,
                        transaction.currency,
                        transaction.category.categoryApplyTo);

        if (existing != null) {
            return existing;
        }

        AccountBalance newAccountBalance = new AccountBalance(
                transaction.account,
                transaction.currency,
                transaction.category.categoryApplyTo);
        return accountBalanceRepo.save(newAccountBalance);
    }


}
