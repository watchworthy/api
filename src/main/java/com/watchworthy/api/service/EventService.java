package com.watchworthy.api.service;

import com.watchworthy.api.dto.EventDTO;
import com.watchworthy.api.model.PageModel;

import java.util.List;

public interface EventService {
    EventDTO getEventDetails(Integer eventId);
    List<EventDTO> getEvents();
    void createEvent(EventDTO eventDTO);
    EventDTO updateEvent(Integer eventId, EventDTO eventDTO);
    void deleteEvent(Integer eventId);


}
