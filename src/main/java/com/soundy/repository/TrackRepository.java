package com.soundy.repository;

import com.soundy.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {

    @Override
    <S extends Track> S save(S entity);

}
