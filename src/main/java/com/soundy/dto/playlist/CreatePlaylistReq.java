package com.soundy.dto.playlist;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CreatePlaylistReq {

    private String name;

    private String desc;

    private Set<Integer> tracks = new HashSet<>();

    private Integer ownerId;

}
