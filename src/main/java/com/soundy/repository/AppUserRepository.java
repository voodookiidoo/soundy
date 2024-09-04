package com.soundy.repository;

import com.soundy.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Override
    <S extends AppUser> S save(S entity);


}
