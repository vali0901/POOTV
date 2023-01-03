package app.actions;

import app.App;
import app.Movie;
import app.Notification;
import app.User;
import app.pages.Page;
import app.pages.PageHierarchy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.ActionInput;
import input.Input;
import output.FinalOutput;
import output.OutputFactory;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public final class ActionMaker {
    private ActionMaker() { }

    private static Stack<Page> pageStack;
    private static Stack<ArrayList<Movie>> moviesStack;

    /**
     * The method that manages all the actions, responsible for calling all
     * the required methods, depending on the action
     * @param input The input from the json file
     * @param output The array node where all the output objects will be stored
     */
    public static void action(final Input input, final ArrayNode output) {
        // init stacks
        pageStack = new Stack();
        moviesStack = new Stack<>();

        for (ActionInput actionInput : input.getActions()) {
            switch (actionInput.getType()) {
                case "on page" -> {
                    boolean success = onPage(actionInput);
                    if (success && (actionInput.getFeature().equals("filter")
                            || actionInput.getFeature().equals("search")
                            || actionInput.getFeature().equals("login")
                            || actionInput.getFeature().equals("register")
                            || actionInput.getFeature().equals("purchase")
                            || actionInput.getFeature().equals("like")
                            || actionInput.getFeature().equals("rate")
                            || actionInput.getFeature().equals("watch"))) {
                        output.addPOJO(OutputFactory.createOutput("log"));
                    } else if (!success) {
                        output.addPOJO(OutputFactory.createOutput("error"));
                    }
                }
                case "change page" -> {
                    Page previousPage = App.getApp().getCurrPage();
                    ArrayList<Movie> previousAvailableMovies = App.getApp().getAvailableMovies();
                    boolean success = changePage(actionInput);
                    if (success && App.getApp().getCurrUser() != null) {
                        pageStack.push(previousPage);
                        ArrayList<Movie> helper = previousAvailableMovies == null ? null
                                : new ArrayList<>(previousAvailableMovies);
                        moviesStack.push(helper);
                    }
                    if (success && (actionInput.getPage().equals("movies")
                            || actionInput.getPage().equals("see details"))) {
                        output.addPOJO(OutputFactory.createOutput("log"));
                    } else if (!success) {
                        output.addPOJO(OutputFactory.createOutput("error"));
                    }
                }
                case "back" -> {
                    if (!pageStack.empty()) {
                        App.getApp().setCurrPage(pageStack.pop());
                        App.getApp().setAvailableMovies(moviesStack.pop());
                        if (App.getApp().getCurrPage().getName().equals("see details")
                                || App.getApp().getCurrPage().getName().equals("movies")) {
                            output.addPOJO(OutputFactory.createOutput("log"));
                        }
                    } else {
                        output.addPOJO(OutputFactory.createOutput("error"));
                    }
                }

                case "database" -> {
                    Movie addedMovie;
                    boolean success;
                    switch (actionInput.getFeature()) {
                        case "add" -> {
                            addedMovie = new Movie(actionInput.getAddedMovie());
                            success = Database.getDatabase().addMovie(addedMovie);
                            if (!success) {
                                output.addPOJO(OutputFactory.createOutput("error"));
                            }
                        }

                        case "delete" -> {
                            success = Database.getDatabase().deleteMovie(actionInput.getDeletedMovie());
                            if (!success) {
                                output.addPOJO(OutputFactory.createOutput("error"));
                            }
                        }

                        default -> {
                        }
                    }
                }

                default -> {
                }
            }
        }

        // the end of actions; send recommendation
        if (App.getApp().getCurrUser() != null
                && App.getApp().getCurrUser().getCredentials().getAccountType().equals("premium")) {
            sendRecommendation(output);
        }
    }

    private static boolean onPage(final ActionInput actionInput) {
        Page currPage = App.getApp().getCurrPage();
        if (!currPage.hasAction(actionInput.getFeature())) {
            return false;
        }

        switch (actionInput.getFeature()) {
            case "login" -> {

                User userLogin = Database.getDatabase().loginUser(actionInput.getCredentials());
                if (userLogin != null) {
                    App.getApp().setCurrUser(userLogin);
                    App.getApp().setCurrPage(PageHierarchy.getPage("homepageAuth"));
                    return true;
                }
                App.getApp().setCurrPage(PageHierarchy.getPage("homepageUnauth"));
                return false;
            }
            case "register" -> {
                User userRegister = Database.getDatabase().registerUser(actionInput.getCredentials());
                if (userRegister != null) {
                    App.getApp().setCurrUser(userRegister);
                    App.getApp().setCurrPage(PageHierarchy.getPage("homepageAuth"));
                    return true;
                }
                App.getApp().setCurrPage(PageHierarchy.getPage("homepageUnauth"));
                return false;
            }
            case "search" -> {
                App.getApp().updateAvailableMovies(actionInput.getStartsWith());
                return true;
            }
            case "filter" -> {
                App.getApp().updateAvailableMovies();
                App.getApp().updateAvailableMovies(actionInput.getFilters());
                return true;
            }
            case "buy tokens" -> {
                return App.getApp().getCurrUser().buyTokens(actionInput.getCount());
            }
            case "buy premium account" -> {
                return App.getApp().getCurrUser().buyPremium();
            }
            case "purchase" -> {
                return App.getApp().getCurrUser().purchaseMovie();
            }
            case "watch" -> {
                return App.getApp().getCurrUser().watchMovie();
            }
            case "like" -> {
                return App.getApp().getCurrUser().likeMovie();
            }
            case "rate" -> {
                return App.getApp().getCurrUser().rateMovie(actionInput.getRate());
            }
            case "subscribe" -> {
               return subscribe(actionInput);
            }
            default -> {
                return false;
            }
        }
    }

    private static boolean changePage(final ActionInput actionInput) {
        Page currPage = App.getApp().getCurrPage();

        if (!currPage.hasChild(actionInput.getPage())) {
            return false;
        }

        switch (actionInput.getPage()) {
            case "see details" -> {
                for (Movie movie : App.getApp().getAvailableMovies()) {
                    if (movie.getName().equals(actionInput.getMovie())) {
                        App.getApp().setCurrPage(PageHierarchy.getPage(actionInput.getPage()));
                        ArrayList<Movie> aux = new ArrayList<>(1);
                        aux.add(movie);
                        App.getApp().setAvailableMovies(aux);
                        return true;
                    }
                }
                return false;
            }
            case "movies" -> {
                App.getApp().setCurrPage(PageHierarchy.getPage(actionInput.getPage()));
                App.getApp().updateAvailableMovies();
                return true;
            }
            case "logout" -> {
                App.getApp().setCurrPage(PageHierarchy.getPage("homepageUnauth"));
                App.getApp().setCurrUser(null);
                App.getApp().setAvailableMovies(null);
                pageStack = new Stack<>();
                moviesStack = new Stack<>();
                return true;
            }
            default -> {
                App.getApp().setCurrPage(PageHierarchy.getPage(actionInput.getPage()));
                return true;
            }
        }
    }

    private static boolean subscribe(final ActionInput actionInput) {
        if (!App.getApp().getCurrPage().getName().equals("see details")) {
            return false;
        }

        if (App.getApp().getCurrUser().getGenres().contains(actionInput.getSubscribedGenre())) {
            return false;
        }

        Movie currentMovie = App.getApp().getAvailableMovies().get(0);
        for (String genre : currentMovie.getGenres()) {
            if (actionInput.getSubscribedGenre().equals(genre)) {
                App.getApp().getCurrUser().subscribe(genre);
                return true;
            }
        }
        return false;
    }

    private static void sendRecommendation(final ArrayNode output) {
        ArrayList<Movie> likedMovies = new ArrayList<>(App.getApp().getCurrUser().getLikedMovies());

        // map the number of appearances of each liked genres to the specific genre
        HashMap<String, Integer> genreAppearances = new HashMap<>();

        for (Movie movie : likedMovies) {
            for (String genre : movie.getGenres()) {
                if (!genreAppearances.containsKey(genre)) {
                    genreAppearances.put(genre, 1);
                } else {
                    genreAppearances.put(genre, genreAppearances.get(genre) + 1);
                }
            }
        }

        // extract the "maximum" genre and add it to the ordered list
        ArrayList<String> orderedGenres = new ArrayList<>();

        while (genreAppearances.size() > 0) {
            String maxGenre = null;
            int maxAppearance = 0;
            for (String currentGenre : genreAppearances.keySet()) {
                if (maxGenre == null
                        || (genreAppearances.get(currentGenre) > maxAppearance)
                        || (genreAppearances.get(currentGenre) == maxAppearance && maxGenre.compareTo(currentGenre) > 0)) {
                    maxGenre = currentGenre;
                    maxAppearance = genreAppearances.get(currentGenre);
                }
            }
            orderedGenres.add(maxGenre);
            genreAppearances.remove(maxGenre);
        }

        // sort the available movies by the likes number
        App.getApp().updateAvailableMovies();
        ArrayList<Movie> currentMovieList = App.getApp().getAvailableMovies();
        Collections.sort(currentMovieList, (o1, o2) -> o2.getNumLikes() - o1.getNumLikes());

        // find the movie to recommend
        Movie recommendation = null;
        for (String genre : orderedGenres) {
            for (Movie movie : currentMovieList) {
                if (!App.getApp().getCurrUser().getWatchedMovies().contains(movie)
                        && movie.getGenres().contains(genre)) {
                    recommendation = movie;
                    App.getApp().getCurrUser().getNotifications()
                            .add(new Notification.Builder(recommendation.getName(), "Recommendation").build());
                    output.addPOJO(new FinalOutput());
                    return;
                }
            }
        }

        // if no movie was found, send an empty recommendation
        App.getApp().getCurrUser().getNotifications()
                .add(new Notification.Builder("No recommendation", "Recommendation").build());
        output.addPOJO(OutputFactory.createOutput("final"));
    }
}
