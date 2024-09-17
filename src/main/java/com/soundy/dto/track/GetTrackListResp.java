package com.soundy.dto.track;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class GetTrackListResp {

    private Set<TrackShortResp> tracks;
}
