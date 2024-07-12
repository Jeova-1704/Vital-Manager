package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.ExameDTO;
import com.vitalManager.vitalManager.model.ExameModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ExameController", description = "Endpoints for Managing Exames")
public interface ExameDocsController {

    @Operation(summary = "Finds all Exames", description = "Finds all Exames",
            tags = {"ExameController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ExameModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<ExameModel>> getAllExames();

    @Operation(summary = "Finds an Exame by ID", description = "Finds an Exame by ID",
            tags = {"ExameController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ExameModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ExameModel> getExameById(@PathVariable int id);

    @Operation(summary = "Adds a new Exame", description = "Adds a new Exame by passing in a JSON representation of the exame",
            tags = {"ExameController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ExameModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ExameModel> createExame(@RequestBody ExameDTO exameDTO);

    @Operation(summary = "Updates an Exame", description = "Updates an Exame by passing in a JSON representation of the exame",
            tags = {"ExameController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ExameModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ExameModel> updateExame(@PathVariable int id, @RequestBody ExameDTO exameDTO);

    @Operation(summary = "Deletes an Exame", description = "Deletes an Exame by ID",
            tags = {"ExameController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deleteExameById(@PathVariable int id);
}
