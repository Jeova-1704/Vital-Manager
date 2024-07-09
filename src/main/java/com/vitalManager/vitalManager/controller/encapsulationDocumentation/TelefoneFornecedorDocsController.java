package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.TelefoneFornecedorDTO;
import com.vitalManager.vitalManager.model.TelefoneFornecedorModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TelefoneFornecedorController", description = "Endpoints for Managing Telefone Fornecedor")
public interface TelefoneFornecedorDocsController {

    @Operation(summary = "Finds all Telefone Fornecedores", description = "Finds all Telefone Fornecedores",
            tags = {"TelefoneFornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TelefoneFornecedorModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<TelefoneFornecedorModel>> getAllPhones();

    @Operation(summary = "Finds a Telefone Fornecedor by Fornecedor ID", description = "Finds a Telefone Fornecedor by Fornecedor ID",
            tags = {"TelefoneFornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TelefoneFornecedorModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<TelefoneFornecedorModel> getPhoneByUserId(@PathVariable int id);

    @Operation(summary = "Adds a new Telefone Fornecedor", description = "Adds a new Telefone Fornecedor by passing in a JSON representation of the phone",
            tags = {"TelefoneFornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = TelefoneFornecedorModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<TelefoneFornecedorModel> createPhone(@RequestBody TelefoneFornecedorDTO phoneDTO);

    @Operation(summary = "Updates a Telefone Fornecedor", description = "Updates a Telefone Fornecedor by passing in a JSON representation of the phone",
            tags = {"TelefoneFornecedorController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TelefoneFornecedorModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<TelefoneFornecedorModel> updateUserPhone(@PathVariable int id, @RequestBody TelefoneFornecedorDTO phoneDTO);

    @Operation(summary = "Deletes a Telefone Fornecedor", description = "Deletes a Telefone Fornecedor by Fornecedor ID",
            tags = {"TelefoneFornecedorController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deletePhoneById(@PathVariable int id);
}
