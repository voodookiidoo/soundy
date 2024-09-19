package com.soundy.repository;

import com.soundy.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {


    @Query("select a from Artist a where a.tracks is not empty")
    Set<Artist> findByTracksNotEmpty();

    @Query("select a from Artist a where a.account.username = ?1")
    @NonNull
    Optional<Artist> findByUsername(String username);

}
