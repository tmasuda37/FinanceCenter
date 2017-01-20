package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.MonthlyCategoryBalance;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.repo.MonthlyCategoryBalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;

@Controller
public class MonthlyCategoryBalanceCtrl {

    @Autowired
    private MonthlyCategoryBalanceRepo monthlyCategoryBalanceRepo;

    @Transactional
    public void updateBalance(Transaction savedTransaction) {
        MonthlyCategoryBalance monthlyCategoryBalance = getBalance(savedTransaction);
        monthlyCategoryBalance.amount = calcBalance(monthlyCategoryBalance, savedTransaction);
        monthlyCategoryBalanceRepo.save(monthlyCategoryBalance);
    }

    public List<MonthlyCategoryBalance> getMonthlyBalance(Account anAccount, Currency currency, Calendar calendar) {
        return monthlyCategoryBalanceRepo.findAllByAccountAndCurrencyAndYearAndMonth(
                anAccount,
                currency,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH));
    }

    protected BigDecimal calcBalance(MonthlyCategoryBalance aBalance, Transaction aTransaction) {
        BigDecimal result;

        result = aBalance.amount.add(aTransaction.amount);

        return result;
    }

    private MonthlyCategoryBalance getBalance(Transaction transaction) {
        MonthlyCategoryBalance existing = monthlyCategoryBalanceRepo
                .findOneByAccountAndCurrencyAndCategoryAndYearAndMonth(
                        transaction.account,
                        transaction.currency,
                        transaction.category,
                        transaction.calendar.get(Calendar.YEAR),
                        transaction.calendar.get(Calendar.MONTH));

        if (existing != null) {
            return existing;
        }

        MonthlyCategoryBalance newMonthlyCategoryBalance = new MonthlyCategoryBalance(
                transaction.account,
                transaction.currency,
                transaction.category,
                transaction.calendar.get(Calendar.YEAR),
                transaction.calendar.get(Calendar.MONTH));

        return monthlyCategoryBalanceRepo.save(newMonthlyCategoryBalance);
    }

}
