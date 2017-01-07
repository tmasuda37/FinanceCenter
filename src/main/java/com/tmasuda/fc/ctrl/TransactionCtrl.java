package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Transaction;
import com.tmasuda.fc.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

@Controller
public class TransactionCtrl {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountBalanceCtrl accountBalanceCtrl;

    @Transactional
    public Transaction createTransaction(Transaction newTransaction) {
        Transaction savedTransaction = transactionRepo.save(newTransaction);

        accountBalanceCtrl.updateBalance(savedTransaction);

        return savedTransaction;
    }

    public Page<Transaction> list(PageRequest aPageRequest, Account anAccount) {
        return transactionRepo.findByAccount(aPageRequest, anAccount);
    }

}
