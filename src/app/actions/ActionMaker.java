package app.actions;

import app.App;
import app.Movie;
import app.User;
import app.pages.Page;
import app.pages.PageHierarchy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.ActionInput;
import input.Input;
import output.Output;
import java.util.ArrayList;

public final class ActionMaker {
    private ActionMaker() { }

    /**
     * The method that manages all the actions, responsible for calling all the required methods, depending on the action
     * @param input The input from the json file
     * @param output The array node where all the output objects will be stored
     */
    public static void action(final Input input, final ArrayNode output) {
        for (ActionInput actionInput : input.getActions()) {
            if (actionInput.getType().equals("on page")) {
                boolean success = onPage(actionInput);
                if (success && (actionInput.getFeature().equals("filter")
                        || actionInput.getFeature().equals("search")
                        || actionInput.getFeature().equals("login")
                        || actionInput.getFeature().equals("register")
                        || actionInput.getFeature().equals("purchase")
                        || actionInput.getFeature().equals("watch")
                        || actionInput.getFeature().equals("like")
                        || actionInput.getFeature().equals("rate"))) {
                    output.addPOJO(new Output(null));
                } else if (!success) {
                    output.addPOJO(new Output("Error"));
                }
            } else if (actionInput.getType().equals("change page")) {
                boolean success = changePage(actionInput);
                if (success && (actionInput.getPage().equals("movies")
                        || actionInput.getPage().equals("see details"))) {
                    //output succes
                    output.addPOJO(new Output(null));
                } else if (!success) {
                    // output error
                    output.addPOJO(new Output("Error"));
                }
            }
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
                return true;
            }
            default -> {
                App.getApp().setCurrPage(PageHierarchy.getPage(actionInput.getPage()));
                return true;
            }
        }
    }
}
