package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Event;
import com.tmasuda.fc.model.EventBalance;
import com.tmasuda.fc.repo.EventBalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Set;

@Controller
public class EventBalanceCtrl {

    @Autowired
    private TransactionCtrl transactionCtrl;

    @Autowired
    private CurrencyCtrl currencyCtrl;

    @Autowired
    private EventBalanceRepo eventBalanceRepo;

    @Transactional
    public void updateBalance(Event event) {
        Set<Currency> currencyList = currencyCtrl.getList();

        currencyList.forEach(currency -> {
            EventBalance eventBalance = getBalance(event, currency);

            BigDecimal amount = transactionCtrl.getTotalExpenseAmountByEvent(event, currency);
            if (amount != null) {
                eventBalance.amount = amount;
            } else {
                eventBalance.amount = BigDecimal.ZERO;
            }

            eventBalanceRepo.save(eventBalance);
        });
    }

    public List<EventBalance> findBalanceListByEvent(Event event) {
        return eventBalanceRepo.findAllByEvent(event);
    }

    private EventBalance getBalance(Event event, Currency currency) {
        EventBalance existing = eventBalanceRepo.findOneByEventAndCurrency(event, currency);

        if (existing != null) {
            return existing;
        }

        EventBalance newEventBalance = new EventBalance(event, currency);

        return eventBalanceRepo.save(newEventBalance);
    }

}
