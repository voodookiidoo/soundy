package com.soundy.dto.track;

import com.soundy.dto.artist.ShortArtistResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Service
public class TrackResp {

    private Integer id;

    private String title;

    private Boolean explicit;

    private Boolean premium;

    private Set<ShortArtistResp> artists = new HashSet<>();

}
