package com.watchworthy.api.dto;

public class AwardDTO {
    private Long id;
    private String name;
    private String category;
    private boolean winner;
    private int year;
    private String description;
    private Long movieId;


    public AwardDTO() {
    }

    public AwardDTO(Long id, String name, String category, boolean winner, int year, String description, Long movieId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.winner = winner;
        this.year = year;
        this.description = description;
        this.movieId = movieId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId != null ? movieId.longValue() : null;
    }
}
