package com.soundy.controller;

import com.soundy.config.JwtUtils;
import com.soundy.controller.operations.AuthOperations;
import com.soundy.dto.user.DelAccountReq;
import com.soundy.dto.user.UserGenTokenReq;
import com.soundy.dto.user.UserRegisterReq;
import com.soundy.entity.Account;
import com.soundy.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.security.auth.login.CredentialException;

@Slf4j
@AllArgsConstructor
@Controller
public class UserController implements AuthOperations {

    JwtUtils jwtUtils;

    private AuthService authService;

    @Override
    public ResponseEntity<?> regUserHandler(@RequestBody UserRegisterReq req) {
        var acc = authService.createUser(req.getUsername(), req.getPassword(), req.getRole());
        String jwt = jwtUtils.generateToken(acc);  // Генерация токена
        return ResponseEntity.ok(jwt);

    }

    @Override
    public ResponseEntity<?> userGenTokenHandler(@RequestBody UserGenTokenReq req) throws CredentialException {
        Account acc = authService.findAndCheckUser(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(jwtUtils.generateToken(acc));

    }

    @Override
    public ResponseEntity<?> delAccountHandler(DelAccountReq req) throws CredentialException {
        var acc = authService.findAndCheckUser(req.getUsername(), req.getPassword());
        authService.delUserByName(acc.getUsername());
        return ResponseEntity.noContent().build();
    }


}
