package com.soundy.dto.artist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString

public class AddArtistReq {

    @JsonProperty
    private String name;

    @JsonProperty
    private String desc;

}
