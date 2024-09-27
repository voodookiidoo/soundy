package com.soundy.service;

import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.exception.PlaylistNotFoundException;
import com.soundy.dto.playlist.CreatePlaylistReq;
import com.soundy.dto.playlist.GetPlayListResp;
import com.soundy.dto.playlist.PlaylistUpdateReq;
import com.soundy.entity.AppUser;
import com.soundy.entity.Playlist;
import com.soundy.entity.Track;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.AppUserRepository;
import com.soundy.repository.PlaylistRepository;
import com.soundy.repository.TrackRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
public class PlaylistService {

    PlaylistRepository playlistRepository;

    TrackRepository trackRepository;

    AppUserRepository userRepository;


    public GetPlayListResp findPlayListById(Integer id) throws PlaylistNotFoundException {
        return playlistRepository.findById(id).map(SoundyMapper.INSTANCE::toPlaylistResp).orElseThrow(
                PlaylistNotFoundException::new
        );
    }

    public void createPlaylist(CreatePlaylistReq req, Principal principal) {
        AppUser owner = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
        Playlist playlist = SoundyMapper.INSTANCE.toPlaylist(req);
        Set<Track> tracks = new HashSet<>(trackRepository.findAllById(req.getTracks()));
//        playlist.setOwner(owner).setTracks(tracks);
        playlistRepository.save(playlist);
    }

    public void updatePlaylist(PlaylistUpdateReq req, Principal principal) throws OwnerInvalidException, PlaylistNotFoundException {
        var playlist = playlistRepository.findById(req.getId()).orElseThrow(PlaylistNotFoundException::new);
        var user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
//        if (!Objects.equals(playlist.getOwner().getId(), user.getId())) {
//            throw new OwnerInvalidException();
//        }
        Set<Track> tracks = new HashSet<>(trackRepository.findAllById(req.getTracks()));
//        playlist.setTracks(tracks).setTitle(req.getName()).setDesc(req.getDesc());
        playlistRepository.save(playlist);

    }

    public void deletePlaylistById(Integer id, Principal principal) throws OwnerInvalidException, PlaylistNotFoundException {
        var playlist = playlistRepository.findById(id).orElseThrow(PlaylistNotFoundException::new);
        var user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(principal.getName()));
//        if (!Objects.equals(playlist.getOwner().getId(), user.getId())) {
//            throw new OwnerInvalidException();
//        }
        playlistRepository.deleteById(id);
    }

}
