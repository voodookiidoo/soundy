package com.soundy.service;

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
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class PlaylistService {

    PlaylistRepository playlistRepository;

    TrackRepository trackRepository;

    AppUserRepository userRepository;

    public Optional<GetPlayListResp> findPlayListById(Integer id) {
        return playlistRepository.findById(id).map(SoundyMapper.INSTANCE::toPlaylistResp);
    }

    public boolean createPlaylist(CreatePlaylistReq req) {
        Optional<AppUser> owner = userRepository.findById(req.getOwnerId());
        Playlist playlist = SoundyMapper.INSTANCE.toPlaylist(req);
        if (owner.isPresent()) {
            Set<Track> tracks = new HashSet<>(trackRepository.findAllById(req.getTracks()));
            playlist.setOwner(owner.get()).setTracks(tracks);
            playlistRepository.save(playlist);
            return true;
        }
        return false;
    }

    public boolean updatePlaylist(PlaylistUpdateReq req) {
        Optional<Playlist> opt = playlistRepository.findById(req.getId());
        if (opt.isPresent()) {
            Set<Track> tracks = new HashSet<>(trackRepository.findAllById(req.getTracks()));
            Playlist playlist = opt.get();
            playlist.setTracks(tracks).setTitle(req.getName()).setDesc(req.getDesc());
            playlistRepository.save(playlist);
            return true;
        }
        return false;
    }

    public void deletePlaylistById(Integer id) {
        playlistRepository.deleteById(id);
    }

}
