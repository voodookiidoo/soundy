package com.soundy.dto.playlist;

import com.soundy.dto.track.TrackShortResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class GetPlayListResp {

    private Integer id;

    private String name;

    private String description;

    private List<TrackShortResp> trackList;


}
