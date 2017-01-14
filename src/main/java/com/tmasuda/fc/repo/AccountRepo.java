package com.tmasuda.fc.repo;

import com.tmasuda.fc.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {

    public Account findOneBySnsId(String snsId);

}
