package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.FornecedorDTO;
import com.vitalManager.vitalManager.model.FornecedorModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FornecedorController", description = "Endpoints for Managing FornecedorController")
public interface FornecedorDocsController {

    @Operation(summary = "Finds all Fornecedores", description = "Finds all Fornecedores",
            tags = {"FornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FornecedorModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<FornecedorModel>> getAllFornecedores();

    @Operation(summary = "Finds a Fornecedor by ID", description = "Finds a Fornecedor by ID",
            tags = {"FornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = FornecedorModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<FornecedorModel> getFornecedorById(@PathVariable int id);

    @Operation(summary = "Adds a new Fornecedor", description = "Adds a new Fornecedor by passing in a JSON representation of the fornecedor",
            tags = {"FornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = FornecedorModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<FornecedorModel> createFornecedor(@RequestBody FornecedorDTO fornecedorDTO);

    @Operation(summary = "Updates a Fornecedor", description = "Updates a Fornecedor by passing in a JSON representation of the fornecedor",
            tags = {"FornecedorController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = FornecedorModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<FornecedorModel> updateFornecedor(@PathVariable int id, @RequestBody FornecedorDTO fornecedorDTO);

    @Operation(summary = "Deletes a Fornecedor", description = "Deletes a Fornecedor by ID",
            tags = {"FornecedorController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deleteFornecedorById(@PathVariable int id);
}
