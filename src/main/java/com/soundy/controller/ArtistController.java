package com.soundy.controller;

import com.soundy.controller.operations.ArtistOperations;
import com.soundy.dto.artist.GetArtistResp;
import com.soundy.mapper.SoundyMapper;
import com.soundy.service.ArtistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class ArtistController implements ArtistOperations {

    private ArtistService artistService;

    @Override
    public ResponseEntity<?> findArtist(Integer id) {
        Optional<GetArtistResp> opt = artistService.findArtistById(id)
                .map(SoundyMapper.INSTANCE::toArtistResp);
        return ResponseEntity.of(opt);

    }


    @Override
    public ResponseEntity<?> loadDbFromFile() {
        return artistService.fillDbFromFile() ? ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }

}
