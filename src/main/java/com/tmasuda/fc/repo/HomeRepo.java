package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.Home;

public interface HomeRepo extends JpaRepository<Home, Long> {
}
