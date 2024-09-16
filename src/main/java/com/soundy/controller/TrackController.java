package com.soundy.controller;

import com.soundy.dto.track.PublishTrackReq;
import com.soundy.service.TrackService;
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

@Slf4j
@AllArgsConstructor
@Controller()
@RequestMapping("/soundy/tracks")
public class TrackController {

    private TrackService trackService;


    @PostMapping("")
    public ResponseEntity<?> publishTrack(@RequestBody PublishTrackReq req) {
        trackService.publishTrack(req);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<?> indexTracks() {
        return ResponseEntity.ok(trackService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findTrack(@PathVariable Integer id) {
        return ResponseEntity.of(trackService.findTrackById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable Integer id) {
        return trackService.delTrackById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
