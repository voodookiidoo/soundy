package com.soundy.controller;

import com.soundy.dto.artist.AddArtistReq;
import com.soundy.dto.artist.GetArtistReq;
import com.soundy.dto.artist.GetArtistResp;
import com.soundy.mapper.SoundyMapper;
import com.soundy.service.ArtistService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import static com.soundy.config.Constants.ADMIN_ROLE;
import static com.soundy.config.Constants.ARTIST_ROLE;
import static com.soundy.config.Constants.USER_ROLE;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/soundy")
public class ArtistController {
    private ArtistService artistService;

    @GetMapping("/artist")
    @RolesAllowed({ARTIST_ROLE, USER_ROLE})
    public ResponseEntity<?> findArtist(@RequestBody GetArtistReq req) {
        Optional<GetArtistResp> opt = artistService.findArtistById(req)
                .map(SoundyMapper.INSTANCE::toArtistResp);
        return ResponseEntity.of(opt);

    }


    @GetMapping("/load")
    @RolesAllowed(ADMIN_ROLE)
    public ResponseEntity<?> loadDbFromFile() {
        return artistService.fillDbFromFile() ? ResponseEntity.noContent().build() : ResponseEntity.internalServerError().build();
    }

}
