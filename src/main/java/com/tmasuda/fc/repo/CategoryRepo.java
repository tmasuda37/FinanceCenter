package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.CategoryApplyTo;
import com.tmasuda.fc.model.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    public Category findOneByPublicIdAndHouseHold(Long publicId, HouseHold houseHold);

    public List<Category> findAllByHouseHoldAndToExpense(HouseHold houseHold, boolean toExpense);

    public List<Category> findAllByHouseHoldAndCategoryApplyTo(HouseHold houseHold, CategoryApplyTo categoryApplyTo);

}
