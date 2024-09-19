package com.soundy.controller;

import com.soundy.config.JwtUtils;
import com.soundy.dto.user.UserGenTokenReq;
import com.soundy.dto.user.UserRegisterReq;
import com.soundy.entity.Account;
import com.soundy.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@AllArgsConstructor
@Controller()
@RequestMapping("/soundy/auth")
public class UserController {

    JwtUtils jwtUtils;

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> regUserHandler(@RequestBody UserRegisterReq req) {
        authService.createUser(req.getUsername(), req.getPassword(), Account.Role.ROLE_USER);
        String jwt = jwtUtils.generateToken(req.getUsername());  // Генерация токена
        return ResponseEntity.ok(jwt);

    }

    @GetMapping("/gentoken")
    public ResponseEntity<?> userGenTokenHandler(@RequestBody UserGenTokenReq req) {
        if (authService.checkUser(req.getUsername(), req.getPassword())) {
            return ResponseEntity.ok(jwtUtils.generateToken(req.getUsername()));
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }


}
