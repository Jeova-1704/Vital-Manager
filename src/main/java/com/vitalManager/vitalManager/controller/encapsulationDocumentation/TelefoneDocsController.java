package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.DTO.TelefoneDTO;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import com.vitalManager.vitalManager.model.TelefoneModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TelefoneController", description = "Endpoints for Managing TelefoneController")
public interface TelefoneDocsController {

    @Operation(summary = "Finds all Addresses", description = "Finds all Addresses",
            tags = {"TelefoneController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TelefoneModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<TelefoneModel>> getAllPhones();

    @Operation(summary = "Finds an Address by User ID", description = "Finds an Address by User ID",
            tags = {"TelefoneController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TelefoneModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<TelefoneModel> getPhoneByUserId(@PathVariable int id);

    @Operation(summary = "Adds a new Address", description = "Adds a new Address by passing in a JSON representation of the address",
            tags = {"TelefoneController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TelefoneModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<TelefoneModel> createPhone(@RequestBody TelefoneDTO userDTO);

    @Operation(summary = "Updates an Address", description = "Updates an Address by passing in a JSON representation of the address",
            tags = {"TelefoneController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = TelefoneModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<TelefoneModel> updateUserPhone(@PathVariable int id, @RequestBody TelefoneDTO userDTO);

    @Operation(summary = "Deletes an Address", description = "Deletes an Address by ID",
            tags = {"TelefoneController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deletePhoneById(@PathVariable int id);
}
