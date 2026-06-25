/*
 * SPDX-FileCopyrightText: 2025 Swiss Confederation
 *
 * SPDX-License-Identifier: MIT
 */

package ch.admin.bj.swiyu.registry.identifier.data.infrastructure.web.controller;

import ch.admin.bj.swiyu.registry.identifier.data.api.ApiErrorDto;
import ch.admin.bj.swiyu.registry.identifier.data.service.DidEntityService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/did")
@AllArgsConstructor
@Tag(name = "DID Controller", description = "Retrieves DID entries from the datastore.")
public class DidController {

    private final DidEntityService didEntityService;

    @Timed
    @GetMapping(value = "/{id}/did.json", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "IF-003.001 - Get a specific file stored for a entry in the datastore.",
        description = "Get a specific file stored for a entry in the datastore."
    )
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true),
            @ApiResponse(
                responseCode = "425",
                description = "Too Early, Resource cannot be shown.",
                content = @Content(schema = @Schema(implementation = ApiErrorDto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Not Found",
                content = @Content(schema = @Schema(implementation = ApiErrorDto.class))
            ),
        }
    )
    public String getDidWebFile(@Valid @PathVariable UUID id) {
        return this.didEntityService.getDidWeb(id);
    }

    @Timed
    @GetMapping(value = "/{id}/did.jsonl", produces = "application/jsonl+json")
    @Operation(
        summary = "IF-003.002 - Get a specific file stored for a entry in the datastore.",
        description = "Get a specific file stored for a entry in the datastore."
    )
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Success", useReturnTypeSchema = true),
            @ApiResponse(
                responseCode = "425",
                description = "Too Early, Resource cannot be shown.",
                content = @Content(schema = @Schema(implementation = ApiErrorDto.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Not Found",
                content = @Content(schema = @Schema(implementation = ApiErrorDto.class))
            ),
        }
    )
    public String getDidTdwFile(@Valid @PathVariable UUID id) {
        return this.didEntityService.getDidTdw(id);
    }
}
