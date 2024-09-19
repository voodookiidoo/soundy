package com.soundy.service;

import com.soundy.dto.exception.OwnerInvalidException;
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

import java.security.Principal;
import java.util.HashSet;
import java.util.Objects;
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

    public boolean createPlaylist(CreatePlaylistReq req, Principal principal) {
        Optional<AppUser> owner = userRepository.findByUsername(principal.getName());
        Playlist playlist = SoundyMapper.INSTANCE.toPlaylist(req);
        if (owner.isPresent()) {
            Set<Track> tracks = new HashSet<>(trackRepository.findAllById(req.getTracks()));
            playlist.setOwner(owner.get()).setTracks(tracks);
            playlistRepository.save(playlist);
            return true;
        }
        return false;
    }

    public boolean updatePlaylist(PlaylistUpdateReq req, Principal principal) throws OwnerInvalidException {
        Optional<AppUser> owner = userRepository.findByUsername(principal.getName());
        Optional<Playlist> opt = playlistRepository.findById(req.getId());
        if (opt.isPresent() && owner.isPresent()) {
            Playlist playlist = opt.get();
            AppUser user = owner.get();
            if (!Objects.equals(playlist.getOwner().getId(), user.getId())) {
                throw new OwnerInvalidException();
            }
            Set<Track> tracks = new HashSet<>(trackRepository.findAllById(req.getTracks()));
            playlist.setTracks(tracks).setTitle(req.getName()).setDesc(req.getDesc());
            playlistRepository.save(playlist);
            return true;
        }
        return false;
    }

    public boolean deletePlaylistById(Integer id, Principal principal) throws OwnerInvalidException {
        var pl = playlistRepository.findById(id);
        var usr = userRepository.findByUsername(principal.getName());
        if (usr.isEmpty() || pl.isEmpty()) {
            return false;
        }
        var user = usr.get();
        var playlist = pl.get();
        if (!Objects.equals(playlist.getOwner().getId(), user.getId())) {
            throw new OwnerInvalidException();
        }
        playlistRepository.deleteById(id);
        return true;
    }

}
