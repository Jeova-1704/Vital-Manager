package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.LoginDTO;
import com.vitalManager.vitalManager.DTO.ResponseDTO;
import com.vitalManager.vitalManager.DTO.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "AuthController", description = "Endpoints for Authentication")
public interface AuthDocsController {

    @Operation(summary = "Login", description = "Login with email and password",
            tags = {"AuthController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content)})
    ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO body);

    @Operation(summary = "Register", description = "Register a new user",
            tags = {"AuthController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content)})
    ResponseEntity<ResponseDTO> register(@RequestBody UsuarioDTO body);
}
