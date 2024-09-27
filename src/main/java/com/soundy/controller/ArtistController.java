package com.soundy.controller;

import com.soundy.config.Constants;
import com.soundy.controller.operations.ArtistOperations;
import com.soundy.dto.artist.ArtistResp;
import com.soundy.mapper.SoundyMapper;
import com.soundy.service.ArtistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(Constants.ARTIST_URL)
public class ArtistController implements ArtistOperations {

    private ArtistService artistService;

    @Override
    public ResponseEntity<?> findArtist(Integer id) {
        Optional<ArtistResp> opt = artistService.findArtistById(id)
                .map(SoundyMapper.INSTANCE::toArtistResp);
        return ResponseEntity.of(opt);

    }


    @Override
    public ResponseEntity<?> loadDbFromFile() {
        return artistService.fillDbFromFile() ? ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }

}
