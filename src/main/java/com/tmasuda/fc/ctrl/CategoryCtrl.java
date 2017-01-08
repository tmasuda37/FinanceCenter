package com.tmasuda.fc.ctrl;

import com.tmasuda.fc.model.Category;
import com.tmasuda.fc.model.Category.CategoryBuilder;
import com.tmasuda.fc.model.CategoryApplyTo;
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

    public void createDefaultCategories(HouseHold houseHold) {
        categoryRepo.save(createWalletExpenseCategoryBuilder(houseHold, "Food").build());
        categoryRepo.save(createBankIncomeCategoryBuilder(houseHold, "Salary").build());
    }

    public Category createCategory(HouseHold houseHold, CategoryApplyTo categoryApplyTo, String name, boolean toExpense) {
        return categoryRepo.save(this.createCategoryBuilder(houseHold, categoryApplyTo, name, toExpense).build());
    }

    public Category createWalletExpenseCategory(HouseHold houseHold, String name) {
        return categoryRepo.save(this.createWalletExpenseCategoryBuilder(houseHold, name).build());
    }

    public Category createWalletIncomeCategory(HouseHold houseHold, String name) {
        return categoryRepo.save(this.createWalletIncomeCategoryBuilder(houseHold, name).build());
    }

    public Category createBankExpenseCategory(HouseHold houseHold, String name) {
        return categoryRepo.save(this.createBankExpenseCategoryBuilder(houseHold, name).build());
    }

    public Category createBankIncomeCategory(HouseHold houseHold, String name) {
        return categoryRepo.save(this.createBankIncomeCategoryBuilder(houseHold, name).build());
    }

    private CategoryBuilder createCategoryBuilder(HouseHold houseHold, CategoryApplyTo categoryApplyTo, String name, boolean toExpense) {
        return new Category.CategoryBuilder(houseHold, categoryApplyTo, name, toExpense);
    }

    private CategoryBuilder createWalletExpenseCategoryBuilder(HouseHold houseHold, String name) {
        return new Category.CategoryBuilder(houseHold, CategoryApplyTo.Wallet, name, true);
    }

    private CategoryBuilder createWalletIncomeCategoryBuilder(HouseHold houseHold, String name) {
        return new Category.CategoryBuilder(houseHold, CategoryApplyTo.Wallet, name, false);
    }

    private CategoryBuilder createBankExpenseCategoryBuilder(HouseHold houseHold, String name) {
        return new Category.CategoryBuilder(houseHold, CategoryApplyTo.Bank, name, true);
    }

    private CategoryBuilder createBankIncomeCategoryBuilder(HouseHold houseHold, String name) {
        return new Category.CategoryBuilder(houseHold, CategoryApplyTo.Bank, name, false);
    }

}
