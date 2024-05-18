package com.homepage.likelion.accounts.repository;


import com.homepage.likelion.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUserId (String userId);
}
