package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.HouseHold;
import com.tmasuda.fc.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryCtrl {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category findCategoryByPublicIdAndHouseHold(Long publicId, HouseHold houseHold) {
        return categoryRepo.findOneByPublicIdAndHouseHold(publicId, houseHold);
    }

    public List<Category> findCategoriesByHouseHold(HouseHold houseHold) {
        return categoryRepo.findAllByHouseHold(houseHold);
    }

    public Category createOrSaveCategory(Category category) {
        return categoryRepo.save(category);
    }

}
