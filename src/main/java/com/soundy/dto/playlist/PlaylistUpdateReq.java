package com.soundy.dto.playlist;

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
public class PlaylistUpdateReq {

    private Integer id;

    private String name;

    private String desc;

    private Set<Integer> tracks = new HashSet<>();

}
