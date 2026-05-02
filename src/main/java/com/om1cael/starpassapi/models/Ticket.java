package com.om1cael.starpassapi.models;

import com.om1cael.starpassapi.enums.TicketType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "tickets")
@Entity
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "eventId", referencedColumnName = "id")
    private Event eventId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @NotNull
    @Min(0)
    private int amount;
}
