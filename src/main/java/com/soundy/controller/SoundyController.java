package com.soundy.controller;

import com.soundy.dto.artist.AddArtistReq;
import com.soundy.dto.artist.GetArtistReq;
import com.soundy.dto.artist.GetArtistResp;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.user.AddUserReq;
import com.soundy.mapper.SoundyMapper;
import com.soundy.service.ArtistService;
import com.soundy.service.PlaylistService;
import com.soundy.service.TrackService;
import com.soundy.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

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


    @GetMapping("/load")
    public ResponseEntity<?> loadDbFromFile() {
        return artistService.loadDbFromFile() ? ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }


    @GetMapping("/artist")
    public ResponseEntity<?> getArtist(@RequestBody GetArtistReq req) {
        Optional<GetArtistResp> opt = artistService.findArtistById(req)
                .map(SoundyMapper.INSTANCE::toArtistResp);
        return ResponseEntity.of(opt);

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
