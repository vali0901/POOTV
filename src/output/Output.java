package output;

import app.App;
import app.Movie;
import app.User;
import input.Credentials;

import java.util.ArrayList;

public final class Output {
    private String error;
    private ArrayList<Movie> currentMoviesList;
    private UserOutput currentUser;

    public Output(final String error) {
        this.error = error;

        if (error == null) {
            if (App.getApp().getAvailableMovies() != null) {
                currentMoviesList = new ArrayList<>();
                for (final Movie movie : App.getApp().getAvailableMovies()) {
                    Movie helper = new Movie(movie);
                    helper.setRating(helper.calculateRating());
                    this.currentMoviesList.add(helper);
                }
            } else {
                this.currentMoviesList = new ArrayList<>(0);
            }
            this.currentUser = new UserOutput(App.getApp().getCurrUser());
        } else {
            this.currentUser = null;
            this.currentMoviesList = new ArrayList<>(0);
        }
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }

    public ArrayList<Movie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(final ArrayList<Movie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public UserOutput getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final UserOutput currentUser) {
        this.currentUser = currentUser;
    }
}


final class  UserOutput {
    private CredentialsOutput credentials;
    private Integer tokensCount;
    private Integer numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;

    UserOutput(final User user) {
        this.credentials = new CredentialsOutput(user.getCredentials());
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();

        purchasedMovies = new ArrayList<>();
        for (Movie movie : user.getPurchasedMovies()) {
            Movie helper = new Movie(movie);
            this.purchasedMovies.add(helper);
        }

        watchedMovies = new ArrayList<>();
        for (Movie movie : user.getWatchedMovies()) {
            Movie helper = new Movie(movie);
            this.watchedMovies.add(helper);
        }

        likedMovies = new ArrayList<>();
        for (Movie movie : user.getLikedMovies()) {
            Movie helper = new Movie(movie);
            this.likedMovies.add(helper);
        }

        ratedMovies = new ArrayList<>();
        for (Movie movie : user.getRatedMovies()) {
            Movie helper = new Movie(movie);
            this.ratedMovies.add(helper);
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

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(final ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(final ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(final ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(final ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
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
