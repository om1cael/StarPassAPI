package com.om1cael.starpassapi.dtos;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequestDTO(
        @NotNull Long ticketId,
        @NotNull int amount
) {
}
