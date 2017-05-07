package com.tmasuda.fc.batch;

import com.tmasuda.fc.ctrl.AccountCtrl;
import com.tmasuda.fc.ctrl.EventBalanceCtrl;
import com.tmasuda.fc.ctrl.EventCtrl;
import com.tmasuda.fc.model.Account;
import com.tmasuda.fc.model.Event;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchProcessManager implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOGGER = Logger.getLogger(BatchProcessManager.class);

    @Autowired
    private AccountCtrl accountCtrl;

    @Autowired
    private EventCtrl eventCtrl;

    @Autowired
    private EventBalanceCtrl eventBalanceCtrl;

    @Scheduled(cron = "0 0 * * * *")
    public void updateEventBalance() {
        LOGGER.info("#updateEventBalance() - START");

        List<Account> accounts = accountCtrl.findAllAccounts();

        accounts.forEach(account -> {
            List<Event> events = eventCtrl.findEventsByHouseHold(account.houseHold);
            events.forEach(event -> {
                eventBalanceCtrl.updateBalance(event);
            });
        });

        LOGGER.info("#updateEventBalance() - Finish");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LOGGER.info("#onApplicationEvent() - Invoke the first batch process");
        updateEventBalance();
    }
}
