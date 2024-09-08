package com.soundy.service;

import com.soundy.dto.track.PublishTrackReq;
import com.soundy.entity.Artist;
import com.soundy.entity.Track;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.TrackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TrackService {

    private TrackRepository trackRepository;

    private ArtistService artistService;


    public void publishTrack(PublishTrackReq req) {
        List<Artist> artists = artistService.findArtistsById(req.getArtists());
        Track track = SoundyMapper.INSTANCE.toTrack(req);
        track.setArtists(Set.copyOf(artists));
        trackRepository.save(track);
    }

}
