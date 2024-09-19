package com.soundy.controller;

import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.playlist.CreatePlaylistReq;
import com.soundy.dto.playlist.GetPlayListResp;
import com.soundy.dto.playlist.PlaylistUpdateReq;
import com.soundy.service.PlaylistService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

import static com.soundy.config.Constants.ARTIST_ROLE;
import static com.soundy.config.Constants.PLAYLIST_URL;
import static com.soundy.config.Constants.USER_ROLE;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(PLAYLIST_URL)
public class PlaylistController {

    private PlaylistService playlistService;

    @GetMapping("/{id}")
    @RolesAllowed({ARTIST_ROLE, USER_ROLE})
    public ResponseEntity<?> getPlaylist(@PathVariable Integer id) {
        Optional<GetPlayListResp> opt = playlistService.findPlayListById(id);
        return ResponseEntity.of(opt);
    }

    @PostMapping
    @RolesAllowed(USER_ROLE)
    public ResponseEntity<?> createPlaylist(@RequestBody CreatePlaylistReq req, Principal principal) {
        return playlistService.createPlaylist(req, principal) ? ResponseEntity.badRequest().build() : ResponseEntity.noContent().build();
    }
    @PatchMapping
    @RolesAllowed(USER_ROLE)
    public ResponseEntity<?> updatePlaylist(@RequestBody PlaylistUpdateReq req, Principal principal) throws OwnerInvalidException {
        return playlistService.updatePlaylist(req, principal) ? ResponseEntity.badRequest().build() : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @RolesAllowed(USER_ROLE)
    public ResponseEntity<?> deletePlaylist(@PathVariable Integer id, Principal principal) throws OwnerInvalidException {
        return playlistService.deletePlaylistById(id, principal) ? ResponseEntity.badRequest().build() : ResponseEntity.noContent().build();
    }

}
