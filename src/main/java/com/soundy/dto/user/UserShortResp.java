package com.soundy.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserShortResp {

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String username;

}
