package com.soundy.dto.track;

import com.soundy.dto.artist.ArtistShortResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private Set<ArtistShortResp> artists = new HashSet<>();

}
