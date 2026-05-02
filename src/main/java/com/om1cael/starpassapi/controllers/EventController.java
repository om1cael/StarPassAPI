package com.om1cael.starpassapi.controllers;

import com.om1cael.starpassapi.models.Event;
import com.om1cael.starpassapi.repositories.EventRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {
    final EventRepository repository;

    public EventController(EventRepository eventRepository) {
        this.repository = eventRepository;
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody @Valid Event event) {
        repository.save(event);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }
}
