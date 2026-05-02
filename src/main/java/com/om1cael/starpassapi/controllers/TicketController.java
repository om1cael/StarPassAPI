package com.om1cael.starpassapi.controllers;

import com.om1cael.starpassapi.dtos.TicketDTO;
import com.om1cael.starpassapi.dtos.TicketResponseDTO;
import com.om1cael.starpassapi.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    final TicketService service;

    public TicketController(TicketService ticketService) {
        this.service = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> create(@RequestBody @Valid TicketDTO ticket) {
        var createdTicket = service.create(ticket);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }
}
