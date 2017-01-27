package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.model.Place;
import com.tmasuda.fc.model.Place.PlaceBuilder;
import com.tmasuda.fc.repo.PlaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlaceCtrl {

    @Autowired
    private PlaceRepo placeRepo;

    public List<Place> findPlacesByHouseHold(HouseHold houseHold) {
        return placeRepo.findAllByHouseHold(houseHold);
    }

    public Place createOrSavePlace(Place place) {
        return placeRepo.save(place);
    }

    private PlaceBuilder createPlaceBuilder(HouseHold houseHold, String name) {
        return new PlaceBuilder(houseHold, name);
    }

}
