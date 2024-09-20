package com.soundy.controller;

import com.soundy.controller.operations.TrackOperations;
import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.service.TrackService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

@Slf4j
@AllArgsConstructor
public class TrackController implements TrackOperations {

    private TrackService trackService;


    @Override
    public ResponseEntity<?> publishTrack(PublishTrackReq req, Principal principal) {
        return trackService.publishTrack(req, principal) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<?> indexTracks() {
        return ResponseEntity.ok(trackService.findAll());
    }


    @Override
    public ResponseEntity<?> findTrack(Integer id) {
        return ResponseEntity.of(trackService.findTrackById(id));
    }


    @Override
    public ResponseEntity<?> deleteTrack(Integer trackId, Principal principal) throws OwnerInvalidException {
        return trackService.delTrackById(trackId, principal) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
