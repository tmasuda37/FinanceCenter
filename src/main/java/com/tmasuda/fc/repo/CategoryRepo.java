package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
