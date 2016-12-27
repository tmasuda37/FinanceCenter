package com.tmasuda.fc.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmasuda.fc.ctrl.AccountBalanceCtrl;
import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.CategoryCtrl;
import com.tmasuda.fc.ctrl.TransactionCtrl;
import com.tmasuda.fc.model.AccountBalance;
import com.tmasuda.fc.model.Transaction;

@RequestMapping("/transaction")
@Controller
public class TransactionHandler {

	@Autowired
	private AccountCtrl accountCtrl;

	@Autowired
	private AccountBalanceCtrl accountBalanceCtrl;

	@Autowired
	private CategoryCtrl categoryCtrl;

	@Autowired
	private TransactionCtrl transactionCtrl;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public AccountBalance create(@RequestAttribute(value = "SNS_ID") String snsId, @RequestBody Transaction aTransaction) throws Exception {
		aTransaction.account = accountCtrl.findAccountBySnsId(snsId);

		if (aTransaction.account == null) {
			throw new Exception("Account Error!");
		}

		aTransaction.category = categoryCtrl.findCategoryByPublicIdAndHouseHold(aTransaction.category.publicId, aTransaction.account.houseHold);

		if (aTransaction.category == null) {
			throw new Exception("Category Error!");
		}

		aTransaction = transactionCtrl.createTransaction(aTransaction);

		return accountBalanceCtrl.getBalance(aTransaction);
	}

}
