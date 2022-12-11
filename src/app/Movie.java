package app;

import input.ContainsInput;
import input.MovieInput;

import java.util.ArrayList;

public final class Movie {
    private String name;
    private Integer year;
    private Integer duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private Integer numLikes;
    private Double rating;
    private Integer numRatings;

    /**
     *
     * @param containsInput The input containing details about actors / genre
     * @return True if this movie contains the given actors / genres, False otherwise
     */
    public boolean contains(final ContainsInput containsInput) {
        if (containsInput == null) {
            return true;
        }

        if (containsInput.getActors() != null) {
            for (String actor : containsInput.getActors()) {
                if (!actors.contains(actor)) {
                    return false;
                }
            }
        }

        if (containsInput.getGenre() != null) {
            for (String genre : containsInput.getGenre()) {
                if (!genres.contains(genre)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * This movie gets a like (increments the numbor of likes)
     */
    public void getLike() {
        this.numLikes++;
    }

    /**
     * This movie gets a rate from a user
     * @param rate The rate this movie gets
     */
    public void getRate(float rate) {
        this.rating += rate;
        this.numRatings++;
    }

    public Movie(final MovieInput movieInput) {
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

    public Movie(final Movie movie) {
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

    /**
     *
     * @return Rating of this movie as a double
     */
    public Double calculateRating() {
        return numRatings == 0 ? 0 : rating / numRatings;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(final ArrayList<String> actors) {
        this.actors = actors;
    }

    public ArrayList<String> getCountriesBanned() {
        return countriesBanned;
    }

    public void setCountriesBanned(final ArrayList<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    public Integer getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(final Integer numLikes) {
        this.numLikes = numLikes;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(final Double rating) {
        this.rating = rating;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final Integer numRatings) {
        this.numRatings = numRatings;
    }
}
