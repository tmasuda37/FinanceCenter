package com.tmasuda.fc.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;

import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.repo.CategoryRepo;

@Controller
public class CategoryCtrl extends AbstractCtrl<Category> {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category getSavedModel(Category instantiated) {
		Example<Category> example = Example.of(instantiated);
		return categoryRepo.findOne(example);
	}

	@Override
	public Category createNewModel(Category instantiated) {
		return categoryRepo.save(instantiated);
	}

	@Override
	public void preRun(Category instantiated) {
	}

	@Override
	public void postRun(Category committed) {
	}

}
