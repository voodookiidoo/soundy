package com.soundy.service;

import com.soundy.dto.user.AddUserReq;
import com.soundy.entity.AppUser;
import com.soundy.mapper.SoundyMapper;
import com.soundy.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    AppUserRepository userRepository;

    public void createUser(AddUserReq req) {
        AppUser user = SoundyMapper.INSTANCE.toAppUser(req);
        userRepository.save(user);
    }

}
