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

    public User(final UserInput userInput) {
        this.credentials = userInput.getCredentials();
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
    }

    public User(final User user) {
        this.credentials = user.getCredentials();
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.purchasedMovies = new ArrayList<>(user.getPurchasedMovies());
        this.watchedMovies = new ArrayList<>(user.getWatchedMovies());
        this.likedMovies = new ArrayList<>(user.getLikedMovies());
        this.ratedMovies = new ArrayList<>(user.getRatedMovies());
    }

    public User(final Credentials credentials) {
        this.credentials = credentials;
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
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
     * @return True if user has enough balance to buy premium account and the action is completed, false otherwise
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

        watchedMovies.add(App.getApp().getAvailableMovies().get(0));

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

        likedMovies.add(App.getApp().getAvailableMovies().get(0));

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

        ratedMovies.add(App.getApp().getAvailableMovies().get(0));

        App.getApp().getAvailableMovies().get(0).getRate(rate);

        return true;
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
}
