package com.soundy.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString
@Accessors(chain = true)
@Getter
@Setter
public class AddUserReq {


    @JsonProperty(value = "username")
    private String name;

}
