package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Event;
import com.tmasuda.fc.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCalendarGreaterThanAndCalendarLessThanAndAccountAndCurrency(Calendar start, Calendar end, Account account, Currency currency);

    Long countByCalendarAndAmountAndDescription(Calendar calendar, BigDecimal amount, String description);

    @Query(value = "select sum(tx.amount) from Transaction tx where tx.event_public_id = ?1 and tx.currency = ?2", nativeQuery = true)
    BigDecimal sumByEventAndCurrency(Event event, Currency currency);

}
