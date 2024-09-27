package com.soundy.dto.artist;

import com.soundy.dto.track.TrackShortResp;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ArtistResp {

    private Integer id;

    private String name;

    private String description;

    private Set<TrackShortResp> tracks = new HashSet<>();


}
