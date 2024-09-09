package com.soundy.dto.track;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class TrackShortResp {

    private Integer id;

    private String name;

    private Boolean premium;

    private Boolean explicit;

}
