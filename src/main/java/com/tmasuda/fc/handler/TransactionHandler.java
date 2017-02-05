package com.tmasuda.fc.handler;

import com.tmasuda.fc.ctrl.AccountBalanceCtrl;
import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CategoryCtrl;
import com.tmasuda.fc.ctrl.TransactionCtrl;
import com.tmasuda.fc.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/transaction")
@Controller
public class TransactionHandler {

    private static final Logger _logger = Logger.getLogger(TransactionHandler.class);

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private AccountBalanceCtrl accountBalanceCtrl;

    @Autowired
    private CategoryCtrl categoryCtrl;

    @Autowired
    private TransactionCtrl transactionCtrl;

    @RequestMapping(value = "/apply-to-list", method = RequestMethod.GET)
    @ResponseBody
    public ApplyTo[] getApplyToList() {
        return ApplyTo.values();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AccountBalance create(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody @Valid Transaction aTransaction) throws Exception {
        aTransaction.account = accountCtrl.findAccountBySnsId(snsId);

        if (aTransaction.account == null) {
            throw new Exception("Account Error!");
        }

        aTransaction.category = categoryCtrl.findCategoryByPublicIdAndHouseHold(aTransaction.category.publicId, aTransaction.account.houseHold);

        if (aTransaction.category == null) {
            throw new Exception("Category Error!");
        }

        transactionCtrl.createOrSaveTransaction(aTransaction);

        return accountBalanceCtrl.getBalance(aTransaction);
    }

    @RequestMapping(value = "/duplicateCheck", method = RequestMethod.POST)
    @ResponseBody
    public List<Transaction> duplicateCheck(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody @Valid List<Transaction> transactionList) throws Exception {
        List<Transaction> uniqueList = new ArrayList<>();

        for (Transaction aTransaction : transactionList) {
            aTransaction.account = accountCtrl.findAccountBySnsId(snsId);

            if (aTransaction.account == null) {
                throw new Exception("Account Error!");
            }

            if (!transactionCtrl.hasSameTransaction(aTransaction)) {
                uniqueList.add(aTransaction);
            }
        }

        return uniqueList;
    }

    @RequestMapping(value = "/createAll", method = RequestMethod.POST)
    @ResponseBody
    public void createAll(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody @Valid List<Transaction> transactionList) throws Exception {
        for (Transaction aTransaction : transactionList) {
            aTransaction.account = accountCtrl.findAccountBySnsId(snsId);

            if (aTransaction.account == null) {
                throw new Exception("Account Error!");
            }

            aTransaction.category = categoryCtrl.findCategoryByPublicIdAndHouseHold(aTransaction.category.publicId, aTransaction.account.houseHold);

            if (aTransaction.category == null) {
                throw new Exception("Category Error!");
            }

            if (!transactionCtrl.hasSameTransaction(aTransaction)) {
                transactionCtrl.createOrSaveTransaction(aTransaction);
            } else {
                _logger.warn("This is skipped as the transaction may be duplicated." + aTransaction.toString());
            }
        }
    }

    @RequestMapping(value = "/list-for-account", method = RequestMethod.POST)
    @ResponseBody
    public List<Transaction> listForAccount(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody TransactionFilter transactionFilter) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        List<Transaction> tmpTxList = transactionCtrl.list(anAccount, transactionFilter.calendar, transactionFilter.currency);
        tmpTxList.forEach(tx -> {
            tx.isMine = (anAccount.publicId == tx.account.publicId);
        });
        return tmpTxList;
    }

    @RequestMapping(value = "/list-for-house-hold", method = RequestMethod.POST)
    @ResponseBody
    public List<Transaction> listForHouseHold(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody TransactionFilter transactionFilter) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        HouseHold aHouseHold = anAccount.houseHold;

        List<Transaction> transactionList = new ArrayList<>();
        aHouseHold.accounts.forEach(account -> {
            List<Transaction> tmpTxList = transactionCtrl.list(account, transactionFilter.calendar, transactionFilter.currency);
            tmpTxList.forEach(tx -> {
                tx.isMine = (anAccount.publicId == tx.account.publicId);
            });
            transactionList.addAll(tmpTxList);
        });

        return transactionList;
    }

    @RequestMapping(value = "/retrieve", method = RequestMethod.POST)
    @ResponseBody
    public Transaction retrieve(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody Transaction transaction) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        return transactionCtrl.retrieve(transaction.publicId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody Transaction transaction) throws Exception {
        Account anAccount = accountCtrl.findAccountBySnsId(snsId);

        if (anAccount == null) {
            throw new Exception("Account Error!");
        }

        transactionCtrl.delete(transaction.publicId);
    }

}
