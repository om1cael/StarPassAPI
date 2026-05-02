package com.om1cael.starpassapi.dtos;

import com.om1cael.starpassapi.enums.TicketType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TicketDTO(
        @NotNull Long eventId,
        @NotNull TicketType ticketType,
        @NotNull @Min(0) BigDecimal price,
        @NotNull @Min(1) int amount
    ) {
}
