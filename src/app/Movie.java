package app;

import input.ContainsInput;
import input.MovieInput;

import java.util.ArrayList;

public class Movie {
    private String name;
    private Integer year;
    private Integer duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private Integer numLikes;
    private Double rating;
    private Integer numRatings;

    public boolean contains(ContainsInput containsInput) {
        if(containsInput == null)
            return true;

        if(containsInput.getActors() != null) {
            for (String actor : containsInput.getActors())
                if (!actors.contains(actor))
                    return false;
        }

        if(containsInput.getGenre() != null) {
            for (String genre : containsInput.getGenre())
                if (!genres.contains(genre))
                    return false;
        }

        return true;
    }

    public Double calculateRating() {
        return numRatings == 0 ? 0 : rating / numRatings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(Integer numLikes) {
        this.numLikes = numLikes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public Movie(MovieInput movieInput) {
        this.name = movieInput.getName();
        this.year = movieInput.getYear();
        this.duration = movieInput.getDuration();
        this.genres = movieInput.getGenres();
        this.actors = movieInput.getActors();
        this.countriesBanned = movieInput.getCountriesBanned();
        this.numLikes = 0;
        this.rating = 0.0;
        this.numRatings = 0;
    }

    public Movie (Movie movie) {
        this.name = movie.getName();
        this.year = movie.getYear();
        this.duration = movie.getDuration();
        this.genres = movie.getGenres();
        this.actors = movie.getActors();
        this.countriesBanned = movie.getCountriesBanned();
        this.numLikes = movie.getNumLikes();
        this.rating = movie.getRating();
        this.numRatings = movie.numRatings;
    }


}
