package com.soundy.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserRemovePlaylistReq {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("playlist_id")
    private Integer playlistId;
}
