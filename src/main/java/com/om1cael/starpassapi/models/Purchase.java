package com.om1cael.starpassapi.models;

import com.om1cael.starpassapi.enums.PurchaseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "ticket_purchases")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticketId", referencedColumnName = "id")
    private Ticket ticketId;

    @NotNull
    private int ticketAmount;

    @NotNull
    private BigDecimal price;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;

    public Purchase(Ticket ticket, int ticketAmount, BigDecimal price, PurchaseStatus purchaseStatus) {
        this.ticketId = ticket;
        this.ticketAmount = ticketAmount;
        this.price = price;
        this.purchaseStatus = purchaseStatus;
    }
}
