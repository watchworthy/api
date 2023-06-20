package com.watchworthy.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tvshow_person")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TvShowPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tvshow_id")
    private TvShow tvshow;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
