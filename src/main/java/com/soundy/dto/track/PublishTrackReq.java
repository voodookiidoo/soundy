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

    @NotEmpty
    @Size(max = 255)
    private String title;

    private Boolean explicit = true;

    private Boolean premium = false;

    @NotEmpty
    private Set<Integer> artists;


}
