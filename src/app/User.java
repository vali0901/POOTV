package app;

import input.Credentials;
import input.UserInput;

import java.util.ArrayList;

public class User {
    private Credentials credentials;
    private Integer tokensCount;
    private Integer numFreePremiumMovies;
    private ArrayList<Movie> purchasedMovies;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> likedMovies;
    private ArrayList<Movie> ratedMovies;

    public User(UserInput userInput) {
        this.credentials = userInput.getCredentials();;
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
    }

    public User(Credentials credentials) {
        this.credentials = credentials;
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
    }

    public boolean buyTokens(int count) {
        if(credentials.getBalance() < count)
            return false;
        credentials.setBalance(credentials.getBalance() - count);
        tokensCount += count;

        return true;
    }

    public boolean buyPremium() {
        if(tokensCount < 10) {
            return false;
        }

        tokensCount -= 10;
        credentials.setAccountType("premium");

        return true;
    }

    public boolean purchaseMovie() {
        if(credentials.getAccountType().equals("standard")) {
            if(tokensCount < 2)
                return false;
            tokensCount -= 2;
            purchasedMovies.add(App.getApp().getAvailableMovies().get(0));
            return true;
        }

        purchasedMovies.add(App.getApp().getAvailableMovies().get(0));
        if(numFreePremiumMovies > 0) {
            numFreePremiumMovies--;
        } else {
            tokensCount -= 2;
        }

        return true;
    }

    public boolean watchMovie() {
        if(!purchasedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            return false;
        }

        watchedMovies.add(App.getApp().getAvailableMovies().get(0));

        return true;
    }

    public boolean likeMovie() {
        if(!watchedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            return false;
        }

        likedMovies.add(App.getApp().getAvailableMovies().get(0));
        App.getApp().getAvailableMovies().get(0).setNumLikes(App.getApp().getAvailableMovies().get(0).getNumLikes() + 1);

        return true;
    }

    public boolean rateMovie(float rate) {
        if(rate < 1 || rate > 5) {
            return false;
        }

        if(!watchedMovies.contains(App.getApp().getAvailableMovies().get(0))) {
            return false;
        }

        ratedMovies.add(App.getApp().getAvailableMovies().get(0));
        App.getApp().getAvailableMovies().get(0).setRating(App.getApp().getAvailableMovies().get(0).getRating() + rate);
        App.getApp().getAvailableMovies().get(0).setNumRatings(App.getApp().getAvailableMovies().get(0).getNumRatings() + 1);

        return true;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Integer getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(Integer tokensCount) {
        this.tokensCount = tokensCount;
    }

    public Integer getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(Integer numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public ArrayList<Movie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(ArrayList<Movie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public ArrayList<Movie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public ArrayList<Movie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(ArrayList<Movie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
}
