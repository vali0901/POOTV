package app;

import app.pages.Page;
import app.pages.PageHierarchy;
import database.Database;
import input.FilterInput;
import input.Input;
import java.util.ArrayList;

public final class App {
    private static final App APP = new App();
    private User currUser;
    private Page currPage;
    private ArrayList<Movie> availableMovies;

    private App() { }

    /**
     * Initializes this app's parameters
     * @param input The json input
     */
    public void start(final Input input) {
        Database.getDatabase().updateDatabase(input);

        currUser = null;
        currPage = PageHierarchy.getPage("homepageUnauth");

        availableMovies = new ArrayList<>();
    }

    /**
     * Updates the available movies, choosing from the Database only the movies available in the current user's country
     */
    public void updateAvailableMovies() {
        availableMovies = new ArrayList<>();

        for (Movie movie : Database.getDatabase().getMovies()) {
            if (movie.getCountriesBanned().contains(currUser.getCredentials().getCountry())) {
                continue;
            }

            availableMovies.add(movie);
        }
    }

    /**
     * Updates the available movies, choosing from the Database only the movies available in the current user's country and starts with the given string
     * @param searchString The string used for searching movies
     */
    public void updateAvailableMovies(final String searchString) {
        if (availableMovies == null) {
            availableMovies = new ArrayList<>();
        }

        ArrayList<Movie> helper = new ArrayList<>();

        for (Movie movie : availableMovies) {
            if (movie.getName().startsWith(searchString)) {
                helper.add(movie);
            }
        }

        availableMovies = helper;
    }

    /**
     * Updates the available movies, choosing from the Database only the movies available in the current user's country and are corresponding to the given filter
     * @param filterInput The filter for choosing the movies
     */
    public void updateAvailableMovies(final FilterInput filterInput) {
        if (availableMovies == null) {
            availableMovies = new ArrayList<>();
        }

        ArrayList<Movie> helper = new ArrayList<>();

        for (Movie movie : availableMovies) {
            if (movie.contains(filterInput.getContains())) {
                helper.add(movie);
            }
        }

        if (filterInput.getSort() == null) {
            availableMovies = helper;
            return;
        }

        helper.sort((o1, o2) -> {
            if (filterInput.getSort().getDuration() == null) {
                if (filterInput.getSort().getRating().equals("decreasing")) {
                    return (int) ((o2.calculateRating() - o1.calculateRating()));
                } else {
                    return (int) ((o1.calculateRating() - o2.calculateRating()));
                }
            }

            if (o1.getDuration() - o2.getDuration() == 0) {
                if (filterInput.getSort().getRating().equals("decreasing")) {
                    return (int) ((o2.calculateRating() - o1.calculateRating()));
                } else {
                    return (int) ((o1.calculateRating() - o2.calculateRating()));
                }
            }

            if (filterInput.getSort().getDuration().equals("decreasing")) {
                return o2.getDuration() - o1.getDuration();
            } else {
                return o1.getDuration() - o2.getDuration();
            }

        });
        availableMovies = helper;
    }

    public static App getApp() {
        return APP;
    }

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(final User currUser) {
        this.currUser = currUser;
    }

    public Page getCurrPage() {
        return currPage;
    }

    public void setCurrPage(final Page currPage) {
        this.currPage = currPage;
    }

    public ArrayList<Movie> getAvailableMovies() {
        return availableMovies;
    }

    public void setAvailableMovies(ArrayList<Movie> availableMovies) {
        this.availableMovies = availableMovies;
    }
}
