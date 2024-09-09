package com.soundy.service;

import com.soundy.dto.playlist.GetPlayListResp;
import com.soundy.dto.playlist.GetPlaylistReq;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PlaylistService {

    PlaylistRepository playlistRepository;

    public Optional<GetPlayListResp> findPlaylist(GetPlaylistReq req) {
        return playlistRepository.findById(req.getId()).map(SoundyMapper.INSTANCE::toPlaylistResp);

    }

}
