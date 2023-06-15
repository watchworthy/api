package com.watchworthy.api.model;

public enum EmailType {
    WELCOME(1L),
    RESET_PASSWORD(2L);

    public final long id;

    EmailType(long id) {
        this.id = id;
    }
}