package com.watchworthy.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "event")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "user_id")
    private Long userId;

//    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Comment> comments;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendee> attendees;



//    public Event() {
//        this.comments = new ArrayList<>();
//    }


//    public void addComment(Comment comment) {
//        comments.add(comment);
//        comment.setEvent(this);
//    }
//
//    public void removeComment(Comment comment) {
//        comments.remove(comment);
//        comment.setEvent(null);
//    }

//    public void addAttendee(Attendee attendee) {
//        attendees.add(attendee);
//        attendee.setEvent(this);
//    }
//
//    public void removeAttendee(Attendee attendee) {
//        attendees.remove(attendee);
//        attendee.setEvent(null);
//    }
}
