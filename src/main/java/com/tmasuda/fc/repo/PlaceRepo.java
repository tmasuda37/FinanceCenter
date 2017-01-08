package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Long> {

    public Place findOneByPublicIdAndHouseHold(Long publicId, HouseHold houseHold);

    public List<Place> findAllByHouseHold(HouseHold houseHold);

}
