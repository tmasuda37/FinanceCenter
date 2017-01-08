package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.Event;
import com.tmasuda.fc.model.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepo extends JpaRepository<Event, Long> {

    public Event findOneByPublicIdAndHouseHold(Long publicId, HouseHold houseHold);

    public List<Event> findAllByHouseHold(HouseHold houseHold);

}
