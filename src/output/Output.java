package output;

import app.App;
import app.Movie;
import app.Notification;
import app.User;
import input.Credentials;

import java.util.ArrayList;

public class Output {
    private String error;
    private ArrayList<MovieOutput> currentMoviesList;
    private UserOutput currentUser;

    public Output() {

    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public ArrayList<MovieOutput> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final ArrayList<MovieOutput> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public UserOutput getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final UserOutput currentUser) {
        this.currentUser = currentUser;
    }
}


final class MovieOutput {
    private String name;
    private String year;
    private Integer duration;
    private ArrayList<String> genres;
    private ArrayList<String> actors;
    private ArrayList<String> countriesBanned;
    private Integer numLikes;
    private Float rating;
    private Integer numRatings;

    public MovieOutput(final Movie movie) {
        this.name = movie.getName();
        this.year = movie.getYear().toString();
        this.duration = movie.getDuration();
        this.genres = movie.getGenres();
        this.actors = movie.getActors();
        this.countriesBanned = movie.getCountriesBanned();
        this.numLikes = movie.getNumLikes();
        this.rating = movie.getRating();
        this.numRatings = movie.getNumRatings();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }
}

final class  UserOutput {
    private CredentialsOutput credentials;
    private Integer tokensCount;
    private Integer numFreePremiumMovies;
    private ArrayList<MovieOutput> purchasedMovies;
    private ArrayList<MovieOutput> watchedMovies;
    private ArrayList<MovieOutput> likedMovies;
    private ArrayList<MovieOutput> ratedMovies;
    private ArrayList<Notification> notifications;

    UserOutput(final User user) {
        this.credentials = new CredentialsOutput(user.getCredentials());
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();

        purchasedMovies = new ArrayList<>();
        for (Movie movie : user.getPurchasedMovies()) {
            MovieOutput helper = new MovieOutput(movie);
            this.purchasedMovies.add(helper);
        }

        watchedMovies = new ArrayList<>();
        for (Movie movie : user.getWatchedMovies()) {
            MovieOutput helper = new MovieOutput(movie);
            this.watchedMovies.add(helper);
        }

        likedMovies = new ArrayList<>();
        for (Movie movie : user.getLikedMovies()) {
            MovieOutput helper = new MovieOutput(movie);
            this.likedMovies.add(helper);
        }

        ratedMovies = new ArrayList<>();
        for (Movie movie : user.getRatedMovies()) {
            MovieOutput helper = new MovieOutput(movie);
            this.ratedMovies.add(helper);
        }

        notifications = new ArrayList<>();
        for (Notification notification : user.getNotifications()) {
            Notification helper = new Notification.Builder(
                    notification.getMovieName(),
                    notification.getMessage())
                    .build();
            this.notifications.add(helper);
        }
    }

    public CredentialsOutput getCredentials() {
        return credentials;
    }

    public void setCredentials(final CredentialsOutput credentials) {
        this.credentials = credentials;
    }

    public Integer getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(final Integer tokensCount) {
        this.tokensCount = tokensCount;
    }

    public Integer getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(final Integer numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<MovieOutput> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<MovieOutput> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<MovieOutput> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<MovieOutput> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<MovieOutput> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<MovieOutput> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<MovieOutput> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<MovieOutput> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }
}

final class CredentialsOutput {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    CredentialsOutput(final Credentials credentials) {
        this.name = credentials.getName();
        this.password = credentials.getPassword();
        this.accountType = credentials.getAccountType();
        this.country = credentials.getCountry();
        this.balance = String.valueOf(credentials.getBalance());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }
}
