package com.soundy.controller.operations;

import com.soundy.config.Constants;
import com.soundy.dto.artist.GetArtistResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Операции с артистами")
@RestController
@RequestMapping(Constants.ARTIST_URL)
public interface ArtistOperations {

    @Operation(summary = "Заполнение бд",
            description = "Метод извлекат данные из csv ресурса, после чего заполняет бд данными")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заполнение успешно")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/load")
//    @RolesAllowed(ADMIN_ROLE)
    ResponseEntity<?> loadDbFromFile();

    @Operation(summary = "Поиск артиста",
            description = "Метод ищет нужного артиста в бд по его id ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Артист найден", content = @Content(schema = @Schema(implementation = GetArtistResp.class)))})
    @GetMapping("/{id}")
//    @RolesAllowed({ARTIST_ROLE, USER_ROLE})
    ResponseEntity<?> findArtist(@PathVariable Integer id);

}
