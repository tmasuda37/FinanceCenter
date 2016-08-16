package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.Place;

public interface PlaceRepo extends JpaRepository<Place, Long> {
}
