package com.watchworthy.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class TestParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "testParent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestChild> testChildEList;

    public TestParent() {
    }

}
