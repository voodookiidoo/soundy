package com.soundy.dto.track;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PublishTrackReq {

    @JsonProperty
    @NotEmpty
    @Size(max = 255)
    private String title;

    @JsonProperty
    private Boolean explicit = true;

    @JsonProperty
    private Boolean premium = false;

    @JsonProperty
    @NotEmpty
    private Set<Integer> artists;


}
