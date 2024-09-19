package com.soundy.controller;

import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.service.TrackService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static com.soundy.config.Constants.ARTIST_ROLE;
import static com.soundy.config.Constants.TRACK_URL;
import static com.soundy.config.Constants.USER_ROLE;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(TRACK_URL)
public class TrackController {

    private TrackService trackService;


    @PostMapping
    @RolesAllowed({ARTIST_ROLE})
    public ResponseEntity<?> publishTrack(@RequestBody PublishTrackReq req, Principal principal) {
        return (trackService.publishTrack(req, principal)) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping
    @RolesAllowed({USER_ROLE, ARTIST_ROLE})
    public ResponseEntity<?> indexTracks() {
        return ResponseEntity.ok(trackService.findAll());
    }

    @GetMapping("/{id}")
    @RolesAllowed({USER_ROLE, ARTIST_ROLE})
    public ResponseEntity<?> findTrack(@PathVariable Integer id) {
        return ResponseEntity.of(trackService.findTrackById(id));
    }

    @DeleteMapping("/{trackId}")
    @RolesAllowed(ARTIST_ROLE)
    public ResponseEntity<?> deleteTrack(@PathVariable Integer trackId, Principal principal) throws OwnerInvalidException {
        return trackService.delTrackById(trackId, principal) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
