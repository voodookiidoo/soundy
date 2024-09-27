package com.soundy.repository;

import com.soundy.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {


    long removeById(Integer id);

    @Query("select t from Track t where :explicit = true or t.explicit = false and t.premium = false or t.premium = :premium")
    List<Track> findByExplicitAndPremium(boolean explicit, boolean premium);


    @Query(value = """
            SELECT id, title, explicit, premium, genre_id, publish_date 
            from track where id in 
                             (select temp.track_id from
                                                       (SELECT track_id ,count(playlist_id) as amount from playlist2track group by track_id ORDER BY amount desc limit :n) 
                                                           as temp)""", nativeQuery = true)
    List<Track> getTopN(int n);

    @Query(value = "SELECT t from Track t order by t.publishDate desc limit :n")
    List<Track> getFreshN(int n);

    @Query(value = """
            select distinct * from track where id in (select distinct track_id from artist2track where artist_id in
                                                             (select artist2track.artist_id from artist2track where track_id = :id))
            """, nativeQuery = true)
    List<Track> findOtherWithArtists(int id);


}
