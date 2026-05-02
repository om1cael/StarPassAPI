package com.om1cael.starpassapi.dtos;

import com.om1cael.starpassapi.enums.TicketType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TicketResponseDTO(
        Long id,
        Long eventId,
        TicketType ticketType,
        BigDecimal price,
        int amount
    ) {
}
