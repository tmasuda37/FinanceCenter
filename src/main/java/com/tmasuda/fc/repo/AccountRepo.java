package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
