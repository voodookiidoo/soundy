package com.soundy.repository;

import com.soundy.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Query("select a from AppUser a where a.account.username = ?1")
    Optional<AppUser> findByUsername(String username);

}
