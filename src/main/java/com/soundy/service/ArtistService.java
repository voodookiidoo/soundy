package com.soundy.service;

import com.soundy.dto.artist.AddArtistReq;
import com.soundy.entity.Artist;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ArtistService {

    ArtistRepository artistRepository;

    public void createArtist(AddArtistReq req) {
        Artist artist = SoundyMapper.INSTANCE.toArtist(req);

        artistRepository.save(artist);
    }

    public List<Artist> findArtistsById(Set<Integer> id){
        return artistRepository.findAllById(id);
    }

}
