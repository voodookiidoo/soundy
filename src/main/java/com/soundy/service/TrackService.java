package com.soundy.service;

import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.track.TrackResp;
import com.soundy.dto.track.TrackShortResp;
import com.soundy.entity.Artist;
import com.soundy.entity.Track;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.AppUserRepository;
import com.soundy.repository.ArtistRepository;
import com.soundy.repository.TrackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class TrackService {

    private final AuthService authService;

    private final AppUserRepository appUserRepository;

    private TrackRepository trackRepository;

    private ArtistRepository artistRepository;

    public boolean delTrackById(Integer id, Principal principal) throws OwnerInvalidException {
        var optTrack = trackRepository.findById(id);
        var optArtist = artistRepository.findByUsername(principal.getName());
        if (optTrack.isEmpty() && optArtist.isEmpty()) {
            return false;
        }
        var track = optTrack.get();
        var artistId = optArtist.get().getId();
        if (track.getArtists().stream().noneMatch(it -> Objects.equals(it.getId(), artistId))) {
            throw new OwnerInvalidException();
        }
        return trackRepository.removeById(id) != 0;
    }

    public boolean publishTrack(PublishTrackReq req, Principal principal) {
        List<Artist> artists = artistRepository.findAllById(req.getArtists());
        var optArtist = artistRepository.findByUsername(principal.getName());
        if (optArtist.isEmpty()){
            return false;
        }
        Track track = SoundyMapper.INSTANCE.toTrack(req);
        Set<Artist> set = new HashSet<>(artists);
        set.add(optArtist.get());
        track.setArtists(set);
        trackRepository.save(track);
        return true;
    }

    public List<TrackShortResp> findAll() {
        return trackRepository.findAll().stream().map(SoundyMapper.INSTANCE::toShortTrackResp).toList();
    }

    public Optional<TrackResp> findTrackById(Integer id) {
        return trackRepository.findById(id).map(SoundyMapper.INSTANCE::toTrackResp);
    }

}
