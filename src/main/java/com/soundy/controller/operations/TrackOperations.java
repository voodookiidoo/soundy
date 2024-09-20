package com.soundy.controller.operations;

import com.soundy.dto.exception.OwnerInvalidException;
import com.soundy.dto.track.PublishTrackReq;
import com.soundy.dto.track.TrackResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static com.soundy.config.Constants.TRACK_URL;

@Tag(name = "Операции с треками")
@RequestMapping(TRACK_URL)
@RestController
public interface TrackOperations {

    @Operation(summary = "Публикация трека",
            description = "Метод создаёт трек в базе, назначая владельцем артиста от которого пришёл запрос, и других артистов, которые были указаны в дто")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Выполнено успешно")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
//    @RolesAllowed({ARTIST_ROLE})
    ResponseEntity<?> publishTrack(@RequestBody PublishTrackReq req, Principal principal);

    @Operation(summary = "Получение списка треков")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно", content = @Content(array = @ArraySchema(arraySchema =
    @Schema(implementation = List.class),
            schema = @Schema(implementation = TrackResp.class))))
    @GetMapping
//    @RolesAllowed({USER_ROLE, ARTIST_ROLE})
    ResponseEntity<?> indexTracks();

    @Operation(summary = "Поиск трека", description = "Выполняет поиск трека в базе по id из пути")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно", content = @Content(schema = @Schema(implementation = TrackResp.class)))
    @GetMapping("/{id}")
//    @RolesAllowed({USER_ROLE, ARTIST_ROLE})
    ResponseEntity<?> findTrack(@PathVariable Integer id);

    @Operation(summary = "Удаление трека", description = "Удаляет трек из базы если пользователь числится среди владельцев")
    @ApiResponse(responseCode = "204", description = "Выполнено успешно")
    @DeleteMapping("/{trackId}")
//    @RolesAllowed(ARTIST_ROLE)
    ResponseEntity<?> deleteTrack(@PathVariable Integer trackId, Principal principal) throws OwnerInvalidException;


}
