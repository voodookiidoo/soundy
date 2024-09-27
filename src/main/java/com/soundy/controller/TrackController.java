package com.soundy.controller;

import com.soundy.controller.operations.TrackOperations;
import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.track.TrackResp;
import com.soundy.service.TrackService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;

import static com.soundy.config.Constants.TRACK_URL;

@Slf4j
@AllArgsConstructor
@RequestMapping(TRACK_URL)
@RestController
public class TrackController implements TrackOperations {

    private TrackService trackService;

    @Override
    public ResponseEntity<?> freshNTracks(Optional<Integer> count) {
        var n = count.orElse(10);
        var tracks = trackService.freshTracks(n);
        return ResponseEntity.ok(tracks);
    }


    @Override
    public ResponseEntity<?> otherTracksWithArtists(Integer id) {
        var tracks = trackService.findOthersWithArtists(id);
        return ResponseEntity.ok(tracks);
    }

    @Override
    public ResponseEntity<?> publishTrack(PublishTrackReq req, Principal principal) {
        return trackService.publishTrack(req, principal) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<?> indexTracks(Principal principal) {

        var tracks = trackService.findAllFiltered(
                // здесь указываются данные для фильтрации
                false, true);
        return ResponseEntity.ok(tracks);
    }


    @Override
    public ResponseEntity<?> findTrack(Integer id, Principal principal) {
        // данные по юзеру
        var userExplicit = false;
        var userPremium = true;
        Optional<TrackResp> opt = trackService.findTrackById(id);
        if (opt.isPresent()) {
            var track = opt.get();
            if ((!track.getExplicit() || userExplicit) && (!track.getPremium() || userPremium)) {
                return ResponseEntity.ok(track);
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<?> deleteTrack(Integer trackId, Principal principal) throws OwnerInvalidException {
        return trackService.delTrackById(trackId, principal) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> topNTracks(Optional<Integer> count) {
        var n = count.orElse(10);
        var tracks = trackService.topTracks(n);
        return ResponseEntity.ok(tracks);
    }


}
