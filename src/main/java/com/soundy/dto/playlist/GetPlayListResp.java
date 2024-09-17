package com.soundy.dto.playlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.soundy.dto.track.TrackShortResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor

public class GetPlayListResp {

    private Integer id;

    private String name;

    private List<TrackShortResp> trackList;

    private Integer listeners;


}
