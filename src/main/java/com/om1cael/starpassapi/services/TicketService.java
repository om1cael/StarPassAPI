package com.om1cael.starpassapi.services;

import com.om1cael.starpassapi.dtos.TicketDTO;
import com.om1cael.starpassapi.dtos.TicketResponseDTO;
import com.om1cael.starpassapi.models.Event;
import com.om1cael.starpassapi.models.Ticket;
import com.om1cael.starpassapi.repositories.EventRepository;
import com.om1cael.starpassapi.repositories.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    final TicketRepository repository;
    final EventRepository eventRepository;

    public TicketService(TicketRepository ticketRepository, EventRepository eventRepository) {
        this.repository = ticketRepository;
        this.eventRepository = eventRepository;
    }

    public TicketResponseDTO create(TicketDTO ticket) {
        Event event = eventRepository.findById(ticket.eventId())
                .orElseThrow(() -> new EntityNotFoundException("Event does not exists"));

        Ticket finalTicket = new Ticket(
                event,
                ticket.ticketType(),
                ticket.price(),
                ticket.amount()
        );

        var createdTicket = repository.save(finalTicket);

        return new TicketResponseDTO(
                createdTicket.getId(),
                createdTicket.getEventId().getId(),
                createdTicket.getTicketType(),
                createdTicket.getPrice(),
                createdTicket.getAmount()
        );
    }
}
