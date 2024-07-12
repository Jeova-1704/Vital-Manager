package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.ProntuarioDTO;
import com.vitalManager.vitalManager.model.ProntuarioModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProntuarioDocsController {
    @Operation(summary = "Finds all ProntuarioController", description = "Finds all ProntuarioController",
            tags = {"ProntuarioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProntuarioModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<ProntuarioModel>> getAllProntuario();

    @Operation(summary = "Finds a prontuario by ID", description = "Finds a prontuario by ID",
            tags = {"ProntuarioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProntuarioModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ProntuarioModel> getProntuarioById(@PathVariable int id);

    @Operation(summary = "Adds a new prontuario", description = "Adds a new prontuario by passing in a JSON representation of the prontuario",
            tags = {"ProntuarioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ProntuarioModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ProntuarioModel> createProntuario(@RequestBody ProntuarioDTO prontuarioDTO);

    @Operation(summary = "Updates a prontuario", description = "Updates a prontuario by passing in a JSON representation of the prontuario",
            tags = {"ProntuarioController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProntuarioModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ProntuarioModel> updateProntuario(@PathVariable int id, @RequestBody ProntuarioDTO prontuarioDTO);

    @Operation(summary = "Deletes a prontuario", description = "Deletes a prontuario by ID",
            tags = {"ProntuarioController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deleteProntuarioById(@PathVariable int id);
}
