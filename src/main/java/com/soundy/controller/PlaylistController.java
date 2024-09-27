package com.soundy.controller;

import com.soundy.controller.operations.PlaylistOperations;
import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.exception.PlaylistNotFoundException;
import com.soundy.dto.playlist.CreatePlaylistReq;
import com.soundy.dto.playlist.PlaylistUpdateReq;
import com.soundy.service.PlaylistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.soundy.config.Constants.PLAYLIST_URL;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(PLAYLIST_URL)
public class PlaylistController implements PlaylistOperations {

    private PlaylistService playlistService;


    @Override
    public ResponseEntity<?> getPlaylist(Integer id) throws PlaylistNotFoundException {
        var resp = playlistService.findPlayListById(id);
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<?> createPlaylist(CreatePlaylistReq req, Principal principal) {
        playlistService.createPlaylist(req, principal);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> updatePlaylist(PlaylistUpdateReq req, Principal principal) throws OwnerInvalidException, PlaylistNotFoundException {
        playlistService.updatePlaylist(req, principal);
        return ResponseEntity.noContent().build();

    }

    @Override
    public ResponseEntity<?> deletePlaylist(Integer id, Principal principal) throws OwnerInvalidException, PlaylistNotFoundException {
        playlistService.deletePlaylistById(id, principal);
        return ResponseEntity.noContent().build();
    }

}
