package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.HouseHold;

public interface HouseHoldRepo extends JpaRepository<HouseHold, Long> {
}
