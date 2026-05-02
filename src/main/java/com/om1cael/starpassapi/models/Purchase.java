package com.om1cael.starpassapi.models;

import com.om1cael.starpassapi.enums.PurchaseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Table(name = "ticket_purchases")
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ticketId", referencedColumnName = "id")
    private Ticket ticketId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus;
}
