package com.watchworthy.api.dto;

public class AwardDTO {
    private Long id;
    private String name;
    private String category;
    private boolean winner;
    private int year;
    private String description;
    private Integer movieId;

    private String posterPath;

    private String movieName;

    // Constructors, getters, and setters

    // Constructors
    public AwardDTO() {
    }

    public AwardDTO(Long id, String name, String category,
                    boolean winner, int year, String description,
                    Integer movieId, String posterPath, String movieName) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.winner = winner;
        this.year = year;
        this.description = description;
        this.movieId = movieId;
        this.movieName = movieName;
        this.posterPath = posterPath;
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

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId ;
    }


    public String getPosterPath(){
        return posterPath;
    }
    public String getMovieName(){
        return movieName;
    }

    public void setPosterPath(String posterPath){
        this.posterPath=posterPath;
    }
    public void setMovieName(String movieName){
        this.movieName = movieName;
    }
}
