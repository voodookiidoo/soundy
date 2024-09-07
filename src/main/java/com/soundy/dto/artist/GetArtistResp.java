package com.soundy.dto.artist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class GetArtistResp {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private Set<ArtistTrackResp> tracks = new HashSet<>();


    @JsonProperty
    private Integer subAmount;

    @Getter
    @Setter
    public static class ArtistTrackResp {

        @JsonProperty
        private Integer id;

        @JsonProperty()
        private String name;

    }

}
