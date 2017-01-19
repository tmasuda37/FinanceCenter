package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.Calendar;

public interface TransactionRepo extends PagingAndSortingRepository<Transaction, Long> {

    Page<Transaction> findByAccount(Pageable aPageable, Account anAccount);

    Long countByCalendarAndAmountAndDescription(Calendar calendar, BigDecimal amount, String description);

}
