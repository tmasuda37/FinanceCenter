package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Event;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;

@Controller
public class TransactionCtrl {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountBalanceCtrl accountBalanceCtrl;

    @Transactional
    public Transaction retrieve(Long publicId) {
        return transactionRepo.findOne(publicId);
    }

    @Transactional
    public void createOrSaveTransaction(Transaction transaction) {
        Transaction existing = null;

        if (transaction.publicId != null) {
            existing = transactionRepo.findOne(transaction.publicId);
        }

        if (existing != null) {
            existing.amount = existing.amount.negate();

            // Account field is ignored in JSON.
            transaction.account = existing.account;

            accountBalanceCtrl.updateBalance(existing);
        }

        if (transaction.event != null && transaction.event.publicId == null) {
            transaction.event = null;
        }

        transactionRepo.save(transaction);

        accountBalanceCtrl.updateBalance(transaction);
    }

    @Transactional
    public void delete(Long publicId) {
        Transaction transaction = transactionRepo.findOne(publicId);
        transactionRepo.delete(transaction);

        transaction.amount = transaction.amount.negate();

        accountBalanceCtrl.updateBalance(transaction);
    }

    public boolean hasSameTransaction(Transaction newTransaction) {
        return transactionRepo.countByCalendarAndAmountAndDescription(newTransaction.calendar, newTransaction.amount, newTransaction.description).intValue() > 0;
    }

    public List<Transaction> list(Account account, Calendar calendar, Currency currency) {
        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        start.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        start.set(Calendar.DAY_OF_MONTH, 1);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        end.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        end.set(Calendar.DAY_OF_MONTH, 1);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        return transactionRepo.findByCalendarGreaterThanAndCalendarLessThanAndAccountAndCurrency(start, end, account, currency);
    }

    public BigDecimal getTotalExpenseAmountByEvent(Event event, Currency currency) {
        return transactionRepo.sumExpensesByEventAndCurrency(event, currency);
    }

}
