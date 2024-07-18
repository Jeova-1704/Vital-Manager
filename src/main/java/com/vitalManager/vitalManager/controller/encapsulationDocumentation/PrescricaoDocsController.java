package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.PrescricaoDTO;
import com.vitalManager.vitalManager.model.PrescricaoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PrescricaoController", description = "Endpoints for Managing PrescricaoController")
public interface PrescricaoDocsController {

    @Operation(summary = "Finds all Prescricoes", description = "Finds all Prescricoes",
            tags = {"PrescricaoController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PrescricaoModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<PrescricaoModel>> getAllPrescricoes();

    @Operation(summary = "Finds a Prescricao by ID", description = "Finds a Prescricao by ID",
            tags = {"PrescricaoController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PrescricaoModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<PrescricaoModel> getPrescricaoById(@PathVariable int id);

    @Operation(summary = "Adds a new Prescricao", description = "Adds a new Prescricao by passing in a JSON representation of the prescricao",
            tags = {"PrescricaoController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = PrescricaoModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<PrescricaoModel> createPrescricao(@RequestBody PrescricaoDTO prescricaoDTO);

    @Operation(summary = "Updates a Prescricao", description = "Updates a Prescricao by passing in a JSON representation of the prescricao",
            tags = {"PrescricaoController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PrescricaoModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<PrescricaoModel> updatePrescricao(@PathVariable int id, @RequestBody PrescricaoDTO prescricaoDTO);

    @Operation(summary = "Deletes a Prescricao", description = "Deletes a Prescricao by ID",
            tags = {"PrescricaoController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deletePrescricaoById(@PathVariable int id);
}
