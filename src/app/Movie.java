package app;

import input.ContainsInput;
import input.MovieInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public final class Movie {
    private String name;
    private Integer year;
    private Integer duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private Integer numLikes;
    private Float rating;
    private Integer numRatings;
    private HashMap<String, Integer> watchedBy;
    private HashMap<String, Float> ratedBy;

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
     * This movie gets a like (increments the number of likes)
     */
    public void getLike() {
        this.numLikes++;
    }

    /**
     * This movie gets a rate from a user
     * Updates or create an entry into the 'ratedBy' hashmap and then updates
     * this movie's rating
     * @param rate The rate this movie gets
     * @param username The name of the user that rated this movie
     */
    public void getRate(final float rate, final String username) {
        if (!ratedBy.containsKey(username)) {
            ratedBy.put(username, rate);
            numRatings++;
        } else {
            ratedBy.put(username, rate);
        }

        rating = calculateRating();
    }

    /**
     * Updates or creates an entry into the 'watchedBy' hashmap
     * @param username The name of the user that watched this movie
     */
    public void gotWatchedBy(final String username) {
        if (!watchedBy.containsKey(username)) {
            watchedBy.put(username, 1);
        } else {
            watchedBy.put(username, watchedBy.get(username) + 1);
        }
    }

    public Movie(final MovieInput movieInput) {
        this.name = movieInput.getName();
        this.year = movieInput.getYear();
        this.duration = movieInput.getDuration();
        this.genres = movieInput.getGenres();
        this.actors = movieInput.getActors();
        this.countriesBanned = movieInput.getCountriesBanned();
        this.numLikes = 0;
        this.rating = 0.00F;
        this.numRatings = 0;
        watchedBy = new HashMap<>();
        ratedBy = new HashMap<>();
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
     * @return Rating of this movie as a float
     */
    public float calculateRating() {
        Collection<Float> ratings =  ratedBy.values();
        float sum = 0;
        for (Float rate : ratings) {
            sum += rate;
        }

        return numRatings == 0 ? 0 : sum / numRatings;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(final Float rating) {
        this.rating = rating;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(final Integer numRatings) {
        this.numRatings = numRatings;
    }

    public HashMap<String, Integer> getWatchedBy() {
        return watchedBy;
    }

    public void setWatchedBy(final HashMap<String, Integer> watchedBy) {
        this.watchedBy = watchedBy;
    }

    public HashMap<String, Float> getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(final HashMap<String, Float> ratedBy) {
        this.ratedBy = ratedBy;
    }
}
