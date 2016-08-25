package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.HouseHoldRepo;

@Controller
public class HouseHoldCtrl extends AbstractCtrl<HouseHold> {

	@Autowired
	private HouseHoldRepo houseHoldRepo;

	@Override
	public HouseHold getSavedModel(HouseHold instantiated) {
		Example<HouseHold> example = Example.of(instantiated);
		return houseHoldRepo.findOne(example);
	}

	@Override
	public HouseHold createNewModel(HouseHold instantiated) {
		return houseHoldRepo.save(instantiated);
	}

	@Override
	public void preRun(HouseHold instantiated) {
	}

	@Override
	public void postRun(HouseHold committed) {
	}

}
