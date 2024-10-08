package com.soundy.dto.user;

import com.soundy.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterReq {

    private String username;
    private String password;

    private Account.Role role;
}
