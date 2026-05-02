package com.om1cael.starpassapi.controllers;

import com.om1cael.starpassapi.dtos.PurchaseRequestDTO;
import com.om1cael.starpassapi.dtos.PurchaseResponseDTO;
import com.om1cael.starpassapi.dtos.TicketDTO;
import com.om1cael.starpassapi.dtos.TicketResponseDTO;
import com.om1cael.starpassapi.services.PurchaseService;
import com.om1cael.starpassapi.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    final PurchaseService service;

    public PurchaseController(PurchaseService purchaseService) {
        this.service = purchaseService;
    }

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> create(@RequestBody @Valid PurchaseRequestDTO purchase) {
        var createdPurchase = service.create(purchase);
        return new ResponseEntity<>(createdPurchase, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponseDTO> create(@PathVariable Long id) {
        var purchase = service.getPurchase(id);
        return new ResponseEntity<>(purchase, HttpStatus.FOUND);
    }
}
