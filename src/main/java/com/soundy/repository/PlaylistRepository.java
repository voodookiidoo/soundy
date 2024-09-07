package com.soundy.repository;

import com.soundy.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    @Override
    <S extends Playlist> S save(S entity);

}
