package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.EstoqueDTO;
import com.vitalManager.vitalManager.model.EstoqueModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "EstoqueController", description = "Endpoints for Managing EstoqueController")
public interface EstoqueDocsController {

    @Operation(summary = "Finds all Estoquees", description = "Finds all Estoquees",
            tags = {"EstoqueController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EstoqueModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<EstoqueModel>> getAllEstoquees();

    @Operation(summary = "Finds a Estoque by ID", description = "Finds a Estoque by ID",
            tags = {"EstoqueController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EstoqueModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<EstoqueModel> getEstoqueById(@PathVariable int id);

    @Operation(summary = "Adds a new Estoque", description = "Adds a new Estoque by passing in a JSON representation of the estoque",
            tags = {"EstoqueController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = EstoqueModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<EstoqueModel> createEstoque(@RequestBody EstoqueDTO estoqueDTO);

    @Operation(summary = "Updates a Estoque", description = "Updates a Estoque by passing in a JSON representation of the estoque",
            tags = {"EstoqueController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = EstoqueModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<EstoqueModel> updateEstoque(@PathVariable int id, @RequestBody EstoqueDTO estoqueDTO);

    @Operation(summary = "Deletes a Estoque", description = "Deletes a Estoque by ID",
            tags = {"EstoqueController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deleteEstoqueById(@PathVariable int id);
}
