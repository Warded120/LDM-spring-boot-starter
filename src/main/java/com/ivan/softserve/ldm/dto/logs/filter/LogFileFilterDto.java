package com.ivan.softserve.ldm.dto.logs.filter;

import jakarta.validation.Valid;
import org.springframework.boot.logging.LogLevel;

public record LogFileFilterDto(
    String fileNameQuery,
    String fileContentQuery,
    @Valid ByteSizeRange byteSizeRange,
    @Valid DateRange dateRange,
    LogLevel logLevel) {
    public static final String defaultJson = """
        {
          "fileNameQuery": "string",
          "fileContentQuery": "string",
          "byteSizeRange": {
            "from": 0,
            "to": 0
          },
          "dateRange": {
            "from": "2025-01-01T00:00:00",
            "to": "2025-01-01T00:00:00"
          },
          "logLevel": "INFO"
        }
        """;
}
