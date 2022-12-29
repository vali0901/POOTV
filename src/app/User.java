package app;

import input.Credentials;
import input.UserInput;

import java.util.ArrayList;

public final class User {
    private Credentials credentials;
    private Integer tokensCount;
    private Integer numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;
    private ArrayList<Notification> notifications;
    private ArrayList<String> genres;


    public User(final UserInput userInput) {
        this.credentials = userInput.getCredentials();
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public User(final User user) {
        this.credentials = user.getCredentials();
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.purchasedMovies = new ArrayList<>(user.getPurchasedMovies());
        this.watchedMovies = new ArrayList<>(user.getWatchedMovies());
        this.likedMovies = new ArrayList<>(user.getLikedMovies());
        this.ratedMovies = new ArrayList<>(user.getRatedMovies());
        this.notifications = new ArrayList<>(user.getNotifications());
        this.genres = new ArrayList<>(user.getGenres());
    }

    public User(final Credentials credentials) {
        this.credentials = credentials;
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    /**
     *
     * @param count Amount of tokens to be bought
     * @return True if the user has enough balance and the action is completed, false otherwise
     */
    public boolean buyTokens(final int count) {
        if (credentials.getBalance() < count) {
            return false;
        }

        credentials.setBalance(credentials.getBalance() - count);
        tokensCount += count;

        return true;
    }

    /**
     *
     * @return True if user has enough balance to buy premium account and the
     * action is completed, false otherwise
     */
    public boolean buyPremium() {
        if (tokensCount < 10) {
            return false;
        }

        tokensCount -= 10;
        credentials.setAccountType("premium");

        return true;
    }

    /**
     * The user is purchasing a movie, this method being called only when the
     * current page is 'See_Details'
     * @return True if the purchase of a movie was completed, False otherwise
     */
    public boolean purchaseMovie() {
        if (purchasedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            return false;
        }

        if (credentials.getAccountType().equals("standard")) {
            if (tokensCount < 2) {
                return false;
            }

            tokensCount -= 2;
            purchasedMovies.add(App.getApp().getAvailableMovies().get(0));

            return true;
        }

        purchasedMovies.add(App.getApp().getAvailableMovies().get(0));

        if (numFreePremiumMovies > 0) {
            numFreePremiumMovies--;
        } else {
            tokensCount -= 2;
        }

        return true;
    }

    /**
     * User is watching a movie, this method must be called only when the
     * current page is 'See_Details'
     * @return True if the movie was previously purchased and the action is
     * completed, false otherwise
     */
    public boolean watchMovie() {
        if (!purchasedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            return false;
        }

        if (!watchedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            watchedMovies.add(App.getApp().getAvailableMovies().get(0));
        }

        App.getApp().getAvailableMovies().get(0).gotWatchedBy(this.credentials.getName());

        return true;
    }

    /**
     * User is watching a movie, this method must be called only when the
     * current page is 'See_Details'
     * @return True if the movie was previously watched and the action is
     * completed, false otherwise
     */
    public boolean likeMovie() {
        if (!watchedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            return false;
        }
        if (!likedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            likedMovies.add(App.getApp().getAvailableMovies().get(0));
        }

        App.getApp().getAvailableMovies().get(0).getLike();
        return true;
    }

    /**
     * User is watching a movie, this method must be called only when the
     * current page is 'See_Details'
     * @return True if the movie was previously watched and the action is
     * completed, false otherwise
     */
    public boolean rateMovie(final float rate) {
        if (rate < 1 || rate > 5) {
            return false;
        }

        if (!watchedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            return false;
        }

        if (!ratedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            ratedMovies.add(App.getApp().getAvailableMovies().get(0));
        }

        App.getApp().getAvailableMovies().get(0).getRate(rate, this.credentials.getName());

        return true;
    }

    /**
     *
     * @param genre The genre that this user is subscribing for
     */
    public void subscribe(final String genre) {
        this.genres.add(genre);
    }

    /**
     * Checks if this user is subscribed to one of the given list containing genres
     * @param genresList A list of genres (generally a movie's genres list)
     * @return True if this user is subscribed to at least one genre, false otherwise
     */
    public boolean isSubscribed(final ArrayList<String> genresList) {
        for (String genre : genresList) {
            if (this.genres.contains(genre)) {
                return true;
            }
        }

        return false;
    }

    /**
     * This user gets a refund, getting its 'money' back
     * This method is called when a movie is deleted from the database
     * @param movie The movie that has been deleted
     */
    public void getRefund(final Movie movie) {
        purchasedMovies.remove(movie);
        watchedMovies.remove(movie);
        likedMovies.remove(movie);
        ratedMovies.remove(movie);

        if (this.credentials.getAccountType().equals("premium")) {
            numFreePremiumMovies++;
        } else {
            tokensCount += 2;
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(final Credentials credentials) {
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

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }
}
