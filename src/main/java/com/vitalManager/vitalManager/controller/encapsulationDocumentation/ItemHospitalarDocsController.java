package com.vitalManager.vitalManager.controller.encapsulationDocumentation;

import com.vitalManager.vitalManager.DTO.ItemHospitalarDTO;
import com.vitalManager.vitalManager.model.ItensHospitalaresModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ItemHospitalarController", description = "Endpoints for Managing Itens Hospitalares")
public interface ItemHospitalarDocsController {

    @Operation(summary = "Finds all Itens Hospitalares", description = "Finds all Itens Hospitalares",
            tags = {"ItemHospitalarController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ItensHospitalaresModel.class)))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<List<ItensHospitalaresModel>> getAllItensHospitalares();

    @Operation(summary = "Finds a Item Hospitalar by ID", description = "Finds a Item Hospitalar by ID",
            tags = {"ItemHospitalarController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ItensHospitalaresModel.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ItensHospitalaresModel> getItemHospitalarById(@PathVariable int id);

    @Operation(summary = "Adds a new Item Hospitalar", description = "Adds a new Item Hospitalar by passing in a JSON representation of the item hospitalar",
            tags = {"ItemHospitalarController"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ItensHospitalaresModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ItensHospitalaresModel> createItemHospitalar(@RequestBody ItemHospitalarDTO itemHospitalarDTO);

    @Operation(summary = "Updates a Item Hospitalar", description = "Updates a Item Hospitalar by passing in a JSON representation of the item hospitalar",
            tags = {"ItemHospitalarController"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ItensHospitalaresModel.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<ItensHospitalaresModel> updateItemHospitalar(@PathVariable int id, @RequestBody ItemHospitalarDTO itemHospitalarDTO);

    @Operation(summary = "Deletes a Item Hospitalar", description = "Deletes a Item Hospitalar by ID",
            tags = {"ItemHospitalarController"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),})
    ResponseEntity<Void> deleteItemHospitalarById(@PathVariable int id);
}
