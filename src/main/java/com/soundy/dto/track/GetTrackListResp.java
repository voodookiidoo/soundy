package com.soundy.dto.track;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class GetTrackListResp {

    private Set<TrackShortResp> tracks;
}
