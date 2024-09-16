package com.soundy.repository;

import com.soundy.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {


    @Query("select a from artist a where a.tracks is not empty")
    Set<Artist> findByTracksNotEmpty();


}
