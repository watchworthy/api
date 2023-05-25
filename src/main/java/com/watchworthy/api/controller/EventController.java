package com.watchworthy.api.controller;

import com.watchworthy.api.dto.EventDTO;
import com.watchworthy.api.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public EventDTO getEventDetails(@PathVariable("id") Integer id) {
        return eventService.getEventDetails(id);
    }

    @GetMapping("/list")
    public List<EventDTO> getEvents() {
        return eventService.getEvents();
    }

    @PostMapping
    public void createEvent(@RequestBody EventDTO eventDTO) {
        eventService.createEvent(eventDTO);
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(@PathVariable("id") Integer id, @RequestBody EventDTO eventDTO) {
        return eventService.updateEvent(id, eventDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable("id") Integer id) {
        eventService.deleteEvent(id);
    }


}
