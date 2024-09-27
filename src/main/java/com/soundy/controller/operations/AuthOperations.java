package com.soundy.controller.operations;

import com.soundy.dto.user.DelAccountReq;
import com.soundy.dto.user.UserGenTokenReq;
import com.soundy.dto.user.UserRegisterReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.CredentialException;


@Tag(name = "Операции с пользователями")
public interface AuthOperations {

    @Operation(summary = "Регистрация пользователя в приложении",
            description = "Метод получает данные из дто, и регистрирует пользователя" +
                    "после чего возвращает jwt токен для дальнейшей авторизации в приложении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Регистрация успешна",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE))})
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/register")
    ResponseEntity<?> regUserHandler(@RequestBody UserRegisterReq req);

    @Operation(summary = "Генерация токена",
            description = "Метод получает данные из дто, и проверяет пользователя на наличие в базе" +
                    "после чего возвращает jwt токен для дальнейшей авторизации в приложении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные верны, токен сгенерирован",
                    content = @Content(
                            mediaType = MediaType.TEXT_PLAIN_VALUE))})
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/gentoken")
    ResponseEntity<?> userGenTokenHandler(@RequestBody UserGenTokenReq req) throws CredentialException;

    @Operation(summary = "Удаление пользователя",
            description = "Метод получает данные из дто, и проверяет пользователя на наличие в базе" +
                    "после чего удаляет его")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Данные верны, пользователь удалён")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/del")
    ResponseEntity<?> delAccountHandler(@RequestBody DelAccountReq req) throws CredentialException;


}
