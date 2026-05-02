package com.om1cael.starpassapi.services;

import com.om1cael.starpassapi.dtos.PaymentRequestDTO;
import com.om1cael.starpassapi.dtos.PurchaseRequestDTO;
import com.om1cael.starpassapi.dtos.PurchaseResponseDTO;
import com.om1cael.starpassapi.enums.PurchaseStatus;
import com.om1cael.starpassapi.exceptions.TicketNotAvailableException;
import com.om1cael.starpassapi.models.Purchase;
import com.om1cael.starpassapi.models.Ticket;
import com.om1cael.starpassapi.repositories.PurchaseRepository;
import com.om1cael.starpassapi.repositories.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseService {
    final PurchaseRepository repository;
    final TicketRepository ticketRepository;
    final RabbitTemplate rabbitTemplate;

    public PurchaseService(PurchaseRepository purchaseRepository, TicketRepository ticketRepository, RabbitTemplate rabbitTemplate) {
        this.repository = purchaseRepository;
        this.ticketRepository = ticketRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public PurchaseResponseDTO create(PurchaseRequestDTO purchase) {
        Ticket ticket = ticketRepository.findById(purchase.ticketId())
                .orElseThrow(() -> new EntityNotFoundException("Ticket does not exists"));

        if(ticket.getAmount() < purchase.amount()) {
            throw new TicketNotAvailableException("Amount not available");
        }

        BigDecimal price = ticket.getPrice().multiply(BigDecimal.valueOf(purchase.amount()));

        Purchase finalPurchase = new Purchase(
                ticket,
                purchase.amount(),
                price,
                PurchaseStatus.RESERVED
        );

        ticket.setAmount(ticket.getAmount() - purchase.amount());

        var createdPurchase = repository.save(finalPurchase);
        ticketRepository.save(ticket);

        sendPaymentRequestMessage(createdPurchase);

        return new PurchaseResponseDTO(
                createdPurchase.getId(),
                createdPurchase.getTicketId().getId(),
                createdPurchase.getTicketAmount(),
                createdPurchase.getPrice()
        );
    }

    private void sendPaymentRequestMessage(Purchase createdPurchase) {
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO(
                createdPurchase.getId(),
                createdPurchase.getPrice()
        );

        rabbitTemplate.convertAndSend( "orders.topic", "order.created", paymentRequestDTO);
    }

    public PurchaseResponseDTO getPurchase(Long id) {
        Purchase purchase = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Purchase not found"));

        return new PurchaseResponseDTO(
                purchase.getId(),
                purchase.getTicketId().getId(),
                purchase.getTicketAmount(),
                purchase.getPrice()
        );
    }
}
