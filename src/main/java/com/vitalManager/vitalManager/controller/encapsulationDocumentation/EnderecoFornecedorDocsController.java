package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.EnderecoFornecedorDTO;
import com.vitalManager.vitalManager.model.EnderecoFornecedorModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "EnderecoFornecedorController", description = "Endpoints for Managing EnderecoFornecedor")
public interface EnderecoFornecedorDocsController {

    @Operation(summary = "Finds all Enderecos", description = "Finds all Enderecos",
            tags = {"EnderecoFornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EnderecoFornecedorModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<EnderecoFornecedorModel>> getAllAddresses();

    @Operation(summary = "Updates a Endereco", description = "Updates a Endereco by passing in a JSON representation of the endereco",
            tags = {"EnderecoFornecedorController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoFornecedorModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<EnderecoFornecedorModel> updateSupplierAddress(@PathVariable int id, @RequestBody EnderecoFornecedorDTO enderecoFornecedorDTO);

    @Operation(summary = "Finds a Endereco by Supplier ID", description = "Finds a Endereco by Supplier ID",
            tags = {"EnderecoFornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EnderecoFornecedorModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<EnderecoFornecedorModel> getAddressBySupplierId(@PathVariable int id);

    @Operation(summary = "Adds a new Endereco", description = "Adds a new Endereco by passing in a JSON representation of the endereco",
            tags = {"EnderecoFornecedorController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = EnderecoFornecedorModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<EnderecoFornecedorModel> createEndereco(@RequestBody EnderecoFornecedorDTO enderecoFornecedorDTO);

    @Operation(summary = "Deletes a Endereco", description = "Deletes a Endereco by ID",
            tags = {"EnderecoFornecedorController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deleteEnderecoById(@PathVariable int id);
}
