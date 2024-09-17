package com.soundy.dto.artist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter

@NoArgsConstructor
public class GetArtistReq {
    @JsonProperty
    Integer id;

}
