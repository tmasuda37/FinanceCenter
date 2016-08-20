package com.tmasuda.fc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmasuda.fc.model.Wallet;

public interface WalletRepo extends JpaRepository<Wallet, Long> {
}
