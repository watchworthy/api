package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.EventDTO;
import com.watchworthy.api.entity.Event;
import com.watchworthy.api.exception.EventNotFoundException;
import com.watchworthy.api.repository.EventRepository;
import com.watchworthy.api.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EventDTO getEventDetails(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event != null) {
            EventDTO eventDTO = modelMapper.map(event, EventDTO.class);
            return eventDTO;
        } else {
            throw new RuntimeException("Event not found with ID: " + eventId);
        }
    }

    @Override
    public List<EventDTO> getEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> modelMapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createEvent(EventDTO eventDTO) {
        Event event = modelMapper.map(eventDTO, Event.class);
        eventRepository.save(event);
    }

    @Override
    public EventDTO updateEvent(Integer eventId, EventDTO eventDTO) {
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event != null) {

            event.setName(eventDTO.getName());
            event.setDate(eventDTO.getDate());
            event.setPosterPath(eventDTO.getPosterPath());

            eventRepository.save(event);

            return modelMapper.map(event, EventDTO.class);
        } else {
            throw new RuntimeException("Event not found with ID: " + eventId);
        }
    }

    @Override
    public void deleteEvent(Integer eventId) {
        eventRepository.deleteById(eventId);
    }
}
