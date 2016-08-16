package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.CategoryApplyTo;
import com.tmasuda.fc.model.Home;
import com.tmasuda.fc.repo.CategoryRepo;

@Controller
public class CategoryCtrl {

	@Autowired
	private CategoryRepo categoryRepo;

	public Category createNewCategory(String name, Home home, CategoryApplyTo categoryApplyTo, boolean expense) {
		Category aCategory = new Category(home, name, categoryApplyTo, expense);
		return categoryRepo.save(aCategory);
	}

}
