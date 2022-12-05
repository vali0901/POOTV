package app.actions;

import app.App;
import app.Movie;
import app.User;
import app.pages.Page;
import app.pages.PageHierarchy;
import com.fasterxml.jackson.databind.node.ArrayNode;
import database.Database;
import input.ActionInput;
import input.Credentials;
import input.Input;
import output.Output;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class ActionMaker {
    public static void action(Input input, ArrayNode output) {
        for(ActionInput actionInput : input.getActions()) {
            if(actionInput.getType().equals("on page")) {
                boolean succes = onPage(actionInput);
                if(succes && (actionInput.getFeature().equals("filter") || actionInput.getFeature().equals("search")
                    || actionInput.getFeature().equals("login") || actionInput.getFeature().equals("register")
                        || actionInput.getFeature().equals("purchase") || actionInput.getFeature().equals("watch")
                        || actionInput.getFeature().equals("like") || actionInput.getFeature().equals("rate"))) {
                   // output succes
                    output.addPOJO(new Output(null));
                } else if (!succes) {
                    // output error
                    output.addPOJO(new Output("Error"));
                }
            } else if(actionInput.getType().equals("change page")) {
                boolean succes = changePage(actionInput);
                if(succes && (actionInput.getPage().equals("movies") || actionInput.getPage().equals("see details"))) {
                    //output succes
                    output.addPOJO(new Output(null));
                } else if(!succes) {
                    // output error
                    output.addPOJO(new Output("Error"));
                }
            }
        }
    }

    private static boolean onPage(ActionInput actionInput) {
        Page currPage = App.getApp().getCurrPage();

        if(!currPage.hasAction(actionInput.getFeature())) {
            return false;
        }

        switch (actionInput.getFeature()) {
            case "login":
                User userLogin = Database.getDatabase().loginUser(actionInput.getCredentials());
                if(userLogin != null) {
                    App.getApp().setCurrUser(userLogin);
                    App.getApp().setCurrPage(App.getApp().getRootAuth());
                    //App.getApp().updateAvailableMovies();
                    return true;
                }

                App.getApp().setCurrPage(App.getApp().getRootUnauth());
                return false;
            case "register":
                User userRegister = Database.getDatabase().registerUser(actionInput.getCredentials());
                if(userRegister != null) {
                    App.getApp().setCurrUser(userRegister);
                    App.getApp().setCurrPage(App.getApp().getRootAuth());
                    //App.getApp().updateAvailableMovies();

                    return true;
                }
                App.getApp().setCurrPage(App.getApp().getRootUnauth());
                return false;
            case "search":
                App.getApp().updateAvailableMovies(actionInput.getStartsWith());
                return true;
            case "filter":
                App.getApp().updateAvailableMovies();
                App.getApp().updateAvailableMovies(actionInput.getFilters());
                return true;
            case "buy tokens":
                return App.getApp().getCurrUser().buyTokens(actionInput.getCount());
            case "buy premium account":
                return App.getApp().getCurrUser().buyPremium();
            case "purchase":
                return App.getApp().getCurrUser().purchaseMovie();
            case "watch":
                return App.getApp().getCurrUser().watchMovie();
            case "like":
                return App.getApp().getCurrUser().likeMovie();
            case "rate":
                return App.getApp().getCurrUser().rateMovie(actionInput.getRate());
            default:
                return false;
        }
    }

    private static boolean changePage(ActionInput actionInput) {
        Page currPage = App.getApp().getCurrPage();

        if(!currPage.hasChild(actionInput.getPage())) {
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
                        //output
                        return true;
                    }
                }
                //App.getApp().setCurrPage(App.getApp().getRootAuth());
                //App.getApp().setAvailableMovies(null);

                return false;
            }
            case "movies" -> {
                App.getApp().setCurrPage(PageHierarchy.getPage(actionInput.getPage()));
                App.getApp().updateAvailableMovies();
                return true;
            }
            case "logout" -> {
                App.getApp().setCurrPage(App.getApp().getRootUnauth());
                App.getApp().setCurrUser(null);
                App.getApp().setAvailableMovies(null);
                return true;
            }
            default -> {
                App.getApp().setCurrPage(PageHierarchy.getPage(actionInput.getPage()));
                return true;
            }
        }
//        if(actionInput.getPage().equals("see details")) {
//            if(currPage.hasChild(actionInput.getPage())) {
//
//            }
//            //eroare nu exista copilu
//            return false;
//        }
//
//        if(currPage.hasChild(actionInput.getPage())) {
//            App.getApp().setCurrPage(PageHierarchy.getPage(actionInput.getPage()));
//            if(currPage.getName().equals("movies")){
//                App.getApp().updateAvailableMovies();
//            }
//            return true;
//        }
//        return false;
    }
}
