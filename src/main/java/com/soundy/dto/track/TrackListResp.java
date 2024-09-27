package com.soundy.dto.track;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TrackListResp {

    private Set<TrackShortResp> tracks;

}
