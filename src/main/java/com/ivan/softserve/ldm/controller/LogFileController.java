package com.ivan.softserve.ldm.controller;

import com.ivan.softserve.ldm.annotations.ApiPageable;
import com.ivan.softserve.ldm.constant.HttpStatuses;
import com.ivan.softserve.ldm.dto.PageableDto;
import com.ivan.softserve.ldm.dto.logs.LogFileMetadataDto;
import com.ivan.softserve.ldm.dto.logs.filter.LogFileFilterDto;
import com.ivan.softserve.ldm.service.dotenv.DotenvService;
import com.ivan.softserve.ldm.service.log.file.LogFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/logs")
public class LogFileController {
    private final LogFileService logFileService;
    private final DotenvService dotenvService;

    public LogFileController(LogFileService logFileService, DotenvService dotenvService) {
        this.logFileService = logFileService;
        this.dotenvService = dotenvService;
    }

    @Operation(summary = "Returns a list of log files metadata from project directory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK,
            content = @Content(schema = @Schema(example = LogFileMetadataDto.defaultJson))),
        @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED,
            content = @Content(examples = @ExampleObject(HttpStatuses.UNAUTHORIZED))),
        @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN,
            content = @Content(examples = @ExampleObject(HttpStatuses.FORBIDDEN))),
        @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND,
            content = @Content(examples = @ExampleObject(HttpStatuses.NOT_FOUND)))
    })
    @ApiPageable
    @PostMapping
    public ResponseEntity<PageableDto<LogFileMetadataDto>> listLogFiles(
        @RequestHeader @NotNull String secretKey,
        @Schema(
            description = "Filters for logs",
            name = "LogFileFilterDto",
            type = "object",
            example = LogFileFilterDto.defaultJson) @RequestBody @NotNull @Valid LogFileFilterDto filterDto,
        @Parameter(hidden = true) Pageable page) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(logFileService.listLogFiles(page, filterDto, secretKey));
    }

    @Operation(summary = "Returns content of a file with given filename")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK,
            content = @Content(schema = @Schema(example = "string"))),
        @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED,
            content = @Content(examples = @ExampleObject(HttpStatuses.UNAUTHORIZED))),
        @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN,
            content = @Content(examples = @ExampleObject(HttpStatuses.FORBIDDEN))),
        @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND,
            content = @Content(examples = @ExampleObject(HttpStatuses.NOT_FOUND))),
        @ApiResponse(responseCode = "503", description = HttpStatuses.SERVICE_UNAVAILABLE,
            content = @Content(examples = @ExampleObject(HttpStatuses.SERVICE_UNAVAILABLE)))
    })
    @GetMapping("/view/{filename}")
    public ResponseEntity<String> viewLogFileContent(
        @RequestHeader @NotNull String secretKey,
        @PathVariable String filename) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(logFileService.viewLogFileContent(logFileService.sanitizeFilename(filename), secretKey));
    }

    @Operation(summary = "Returns a url that triggers file download in a browser")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK,
            content = @Content(schema = @Schema(example = HttpStatuses.OK))),
        @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED,
            content = @Content(examples = @ExampleObject(HttpStatuses.UNAUTHORIZED))),
        @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN,
            content = @Content(examples = @ExampleObject(HttpStatuses.FORBIDDEN))),
        @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND,
            content = @Content(examples = @ExampleObject(HttpStatuses.NOT_FOUND)))
    })
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadLogFile(
        @RequestHeader @NotNull String secretKey,
        @PathVariable String filename) {
        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + logFileService.sanitizeFilename(filename) + "\"")
            .body(logFileService.generateDownloadLogFileUrl(logFileService.sanitizeFilename(filename), secretKey));
    }

    @Operation(summary = "deletes '.env' file to make functionality that is dependent on it unavailable")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = HttpStatuses.OK,
            content = @Content(schema = @Schema(example = HttpStatuses.OK))),
        @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED,
            content = @Content(examples = @ExampleObject(HttpStatuses.UNAUTHORIZED))),
        @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN,
            content = @Content(examples = @ExampleObject(HttpStatuses.FORBIDDEN)))
    })
    @DeleteMapping("/delete-dotenv")
    public ResponseEntity<Object> deleteDotenvFile(
        @RequestHeader @NotNull String secretKey) {
        dotenvService.deleteDotenvFile(secretKey);
        return ResponseEntity.ok().build();
    }
}