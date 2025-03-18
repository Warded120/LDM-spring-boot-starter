package com.ivan.softserve.ldm.dto.logs.filter;

import com.ivan.softserve.ldm.exception.exceptions.BadRequestException;
import jakarta.validation.constraints.Min;

public record ByteSizeRange(
    @Min(value = 0, message = "Size cannot be negative") long from,
    @Min(value = 0, message = "Size cannot be negative") long to) {
    public ByteSizeRange {
        if (from > to) {
            throw new BadRequestException("'from' size must be less or equal to 'to' size");
        }
    }
}
