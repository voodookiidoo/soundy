package com.soundy.dto.artist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.soundy.dto.track.TrackShortResp;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class GetArtistResp {

    private Integer id;

    private String name;

    private String description;

    private Set<TrackShortResp> tracks = new HashSet<>();

    @JsonProperty(value = "subscriber_count")
    private Integer subAmount;

}
