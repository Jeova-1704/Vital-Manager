package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.EnderecoUsuarioDTO;
import com.vitalManager.vitalManager.model.EnderecoUsuarioModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "EnderecoUsuarioController", description = "Endpoints for Managing EnderecoUsuarioController")
public interface EnderecoUsuarioDocsController {

    @Operation(summary = "Finds all Addresses", description = "Finds all Addresses",
            tags = {"EnderecoUsuarioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EnderecoUsuarioModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<EnderecoUsuarioModel>> getAllAdress();

    @Operation(summary = "Finds an Address by User ID", description = "Finds an Address by User ID",
            tags = {"EnderecoUsuarioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoUsuarioModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<EnderecoUsuarioModel> getAdressByUserId(@PathVariable int id);

    @Operation(summary = "Adds a new Address", description = "Adds a new Address by passing in a JSON representation of the address",
            tags = {"EnderecoUsuarioController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoUsuarioModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<EnderecoUsuarioModel> createUser(@RequestBody EnderecoUsuarioDTO userDTO);

//    @Operation(summary = "Updates an Address", description = "Updates an Address by passing in a JSON representation of the address",
//            tags = {"EnderecoUsuarioController"},
//            responses = {
//                    @ApiResponse(description = "Updated", responseCode = "200",
//                            content = @Content(schema = @Schema(implementation = EnderecoUsuarioModel.class))),
//                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
//    ResponseEntity<EnderecoUsuarioModel> updateUser(@PathVariable int id, @RequestBody EnderecoUsuarioModel userDTO);
//
//    @Operation(summary = "Deletes an Address", description = "Deletes an Address by ID",
//            tags = {"EnderecoUsuarioController"},
//            responses = {
//                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
//                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
//                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
//                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
//    ResponseEntity<Void> deleteUserById(@PathVariable int id);
}
