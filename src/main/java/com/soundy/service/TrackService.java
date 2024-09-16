package com.soundy.service;

import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.track.TrackResp;
import com.soundy.dto.track.TrackShortResp;
import com.soundy.entity.Artist;
import com.soundy.entity.Track;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.TrackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TrackService {

    private TrackRepository trackRepository;

    private ArtistService artistService;

    public boolean delTrackById(Integer id) {
        return trackRepository.removeById(id) != 0;
    }

    public void publishTrack(PublishTrackReq req) {
        List<Artist> artists = artistService.findArtistsById(req.getArtists());
        Track track = SoundyMapper.INSTANCE.toTrack(req);
        track.setArtists(Set.copyOf(artists));
        trackRepository.save(track);
    }

    public List<TrackShortResp> findAll() {
        return trackRepository.findAll().stream().map(SoundyMapper.INSTANCE::toShortTrackResp).toList();
    }

    public Optional<TrackResp> findTrackById(Integer id) {
        return trackRepository.findById(id).map(SoundyMapper.INSTANCE::toTrackResp);
    }

}
