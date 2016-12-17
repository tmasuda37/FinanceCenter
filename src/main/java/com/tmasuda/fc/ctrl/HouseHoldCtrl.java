package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.HouseHoldRepo;

@Controller
public class HouseHoldCtrl {

	@Autowired
	private HouseHoldRepo houseHoldRepo;

	public HouseHold findHouseHoldByHouseHoldId(String houseHoldId) {
		return houseHoldRepo.findOne(houseHoldId);
	}

	public HouseHold createHouseHold(String houseHoldId) {
		HouseHold existing = this.findHouseHoldByHouseHoldId(houseHoldId);
		if (existing != null) {
			return existing;
		}

		HouseHold newHouseHold = new HouseHold();
		newHouseHold.houseHoldId = houseHoldId;
		return houseHoldRepo.save(newHouseHold);
	}
}
