package com.om1cael.starpassapi.dtos;

import java.math.BigDecimal;

public record PurchaseResponseDTO(
        Long id,
        Long ticketId,
        int amount,
        BigDecimal price
) {
}
