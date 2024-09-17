package com.soundy.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserEditPlaylistReq {

    @JsonProperty("user_id")
    private Integer userId;

    private Set<Integer> tracks = new HashSet<>();

}
