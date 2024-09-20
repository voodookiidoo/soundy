package com.soundy.repository;

import com.soundy.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @NonNull
    Optional<Account> findByUsername(String username);

}
