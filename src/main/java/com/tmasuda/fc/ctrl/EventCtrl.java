package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Event;
import com.tmasuda.fc.model.Event.EventBuilder;
import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EventCtrl {

    @Autowired
    private EventRepo eventRepo;

    public List<Event> findEventsByHouseHold(HouseHold houseHold) {
        return eventRepo.findAllByHouseHold(houseHold);
    }

    public Event createEvent(HouseHold houseHold, String name) {
        return eventRepo.save(this.createEventBuilder(houseHold, name).build());
    }

    private EventBuilder createEventBuilder(HouseHold houseHold, String name) {
        return new EventBuilder(houseHold, name);
    }

}
