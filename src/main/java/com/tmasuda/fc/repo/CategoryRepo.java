package com.tmasuda.fc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.HouseHold;

public interface CategoryRepo extends JpaRepository<Category, Long> {

	public Category findOneByPublicIdAndHouseHold(Long publicId, HouseHold houseHold);

	public List<Category> findAllByHouseHoldAndToExpense(HouseHold houseHold, boolean toExpense);

	public List<Category> findAllByHouseHold(HouseHold houseHold);

}
