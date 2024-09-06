package com.soundy.controller;

import com.soundy.dto.artist.AddArtistReq;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.user.AddUserReq;
import com.soundy.service.ArtistService;
import com.soundy.service.PlaylistService;
import com.soundy.service.TrackService;
import com.soundy.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/soundy")
public class SoundyController {

    PlaylistService playlistService;

    TrackService trackService;

    ArtistService artistService;

    UserService userService;

    @PostMapping("/usr")
    public ResponseEntity<?> createUser(@RequestBody AddUserReq req) {
        userService.createUser(req);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/artist")
    public ResponseEntity<?> createArtist(@RequestBody AddArtistReq req) {
        artistService.createArtist(req);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/playlist")
    public ResponseEntity<?> createPlaylist(@RequestBody AddArtistReq req) {
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/track")
    public ResponseEntity<?> publishTrack(@RequestBody PublishTrackReq req) {
        trackService.publishTrack(req);
        return ResponseEntity.noContent().build();
    }


}
