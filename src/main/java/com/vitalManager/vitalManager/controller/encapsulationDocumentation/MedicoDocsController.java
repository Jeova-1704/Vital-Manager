package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.MedicoDTO;
import com.vitalManager.vitalManager.model.MedicoModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "MedicoController", description = "Endpoints for Managing MedicoController")
public interface MedicoDocsController {

    @Operation(summary = "Finds all Medicos", description = "Finds all Medicos",
            tags = {"MedicoController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MedicoModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<MedicoModel>> getAllMedicos();

    @Operation(summary = "Finds a Medico by ID", description = "Finds a Medico by ID",
            tags = {"MedicoController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MedicoModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<MedicoModel> getMedicoById(@PathVariable int id);

    @Operation(summary = "Adds a new Medico", description = "Adds a new Medico by passing in a JSON representation of the medico",
            tags = {"MedicoController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = MedicoModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<MedicoModel> createMedico(@RequestBody MedicoDTO medicoDTO);

    @Operation(summary = "Updates a Medico", description = "Updates a Medico by passing in a JSON representation of the medico",
            tags = {"MedicoController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = MedicoModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<MedicoModel> updateMedico(@PathVariable int id, @RequestBody MedicoModel medicoDTO);

    @Operation(summary = "Deletes a Medico", description = "Deletes a Medico by ID",
            tags = {"MedicoController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deleteMedicoById(@PathVariable int id);
}
