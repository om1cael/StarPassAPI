package com.om1cael.starpassapi.dtos;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        Long purchaseId,
        int amount,
        BigDecimal price
) {
}
