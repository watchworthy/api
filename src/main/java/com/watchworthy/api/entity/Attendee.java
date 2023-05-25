package com.watchworthy.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attendee")
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


}

