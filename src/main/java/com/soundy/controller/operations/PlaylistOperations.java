package com.soundy.controller.operations;

import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.exception.PlaylistNotFoundException;
import com.soundy.dto.playlist.CreatePlaylistReq;
import com.soundy.dto.playlist.GetPlayListResp;
import com.soundy.dto.playlist.PlaylistUpdateReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static com.soundy.config.Constants.PLAYLIST_URL;

@RestController
@RequestMapping(PLAYLIST_URL)
@Tag(name = "Действия с плейлистами")
public interface PlaylistOperations {

    @Operation(summary = "Поиск плейлиста",
            description = "Метод возвращает плейлист по заданному id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполнено успешно",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = GetPlayListResp.class))),
            @ApiResponse(responseCode = "404", description = "Не найдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PlaylistNotFoundException.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
//    @RolesAllowed({ARTIST_ROLE, USER_ROLE})
    ResponseEntity<?> getPlaylist(@PathVariable Integer id) throws PlaylistNotFoundException;


    @Operation(summary = "Создание плейлиста",
            description = "Метод создаёт плейлист, назначая владельцем пользователя от которого пришёл запрос на создание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Выполнено успешно")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/pub")
//    @RolesAllowed(USER_ROLE)
    ResponseEntity<?> createPlaylist(@RequestBody CreatePlaylistReq req, Principal principal);

    @Operation(summary = "Обновление плейлиста",
            description = "Метод создаёт плейлист, назначая владельцем пользователя от которого пришёл запрос на создание")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Выполнено успешно")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/upd")
//    @RolesAllowed(USER_ROLE)
    ResponseEntity<?> updatePlaylist(@RequestBody PlaylistUpdateReq req, Principal principal) throws OwnerInvalidException, PlaylistNotFoundException;

    @Operation(summary = "Удаление плейлиста",
            description = "Метод удаляет плейлист по заданному id, в случае если пользователь является владельцем плейлиста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Выполнено успешно")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
//    @RolesAllowed(USER_ROLE)
    ResponseEntity<?> deletePlaylist(@PathVariable Integer id, Principal principal) throws OwnerInvalidException, PlaylistNotFoundException;


}
