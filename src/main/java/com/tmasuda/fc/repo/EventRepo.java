package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.Event;

public interface EventRepo extends JpaRepository<Event, Long> {
}
