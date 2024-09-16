package com.soundy.controller;

import com.soundy.dto.playlist.CreatePlaylistReq;
import com.soundy.dto.playlist.GetPlayListResp;
import com.soundy.service.PlaylistService;
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

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/soundy/playlist")
public class PlaylistController {

    private PlaylistService playlistService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaylist(@PathVariable Integer id) {
        Optional<GetPlayListResp> opt = playlistService.findPlayListById(id);
        return ResponseEntity.of(opt);
    }

    @PostMapping("")
    public ResponseEntity<?> createPlaylist(@RequestBody CreatePlaylistReq req) {
        return playlistService.createPlaylist(req) ? ResponseEntity.badRequest().build() : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Integer id){
        playlistService.deletePlaylistById(id);
        return ResponseEntity.noContent().build();
    }

}
