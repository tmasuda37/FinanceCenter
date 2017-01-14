package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.HouseHold;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseHoldRepo extends JpaRepository<HouseHold, String> {
}
