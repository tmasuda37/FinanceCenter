package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.CategoryApplyTo;
import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.CategoryRepo;

@Controller
public class CategoryCtrl {

	@Autowired
	private CategoryRepo categoryRepo;

	public Category createNewCategory(HouseHold household, CategoryApplyTo categoryApplyTo, String name, boolean toExpense) {
		Category aCategory = new Category(household, categoryApplyTo, name, toExpense);
		return categoryRepo.save(aCategory);
	}

}
