package com.soundy.controller;

import com.soundy.config.Constants;
import com.soundy.config.JwtService;
import com.soundy.controller.operations.AuthOperations;
import com.soundy.dto.user.DelAccountReq;
import com.soundy.dto.user.UserGenTokenReq;
import com.soundy.dto.user.UserRegisterReq;
import com.soundy.entity.Account;
import com.soundy.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;

@Slf4j
@AllArgsConstructor
@RequestMapping(Constants.AUTH_URL)
@RestController
public class UserController implements AuthOperations {

    JwtService jwtService;

    private AuthService authService;

    @Override
    public ResponseEntity<?> regUserHandler(@RequestBody UserRegisterReq req) {
        var acc = authService.createUser(req.getUsername(), req.getPassword(), req.getRole());
        String jwt = jwtService.generateToken(acc);  // Генерация токена
        return ResponseEntity.ok(jwt);

    }

    @Override
    public ResponseEntity<?> userGenTokenHandler(@RequestBody UserGenTokenReq req) throws CredentialException {
        Account acc = authService.findAndCheckUser(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(jwtService.generateToken(acc));

    }

    @Override
    public ResponseEntity<?> delAccountHandler(DelAccountReq req) throws CredentialException {
        var acc = authService.findAndCheckUser(req.getUsername(), req.getPassword());
        authService.delUserByName(acc.getUsername());
        return ResponseEntity.noContent().build();
    }


}
