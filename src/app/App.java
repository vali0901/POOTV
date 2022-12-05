package app;

import app.pages.Page;
import app.pages.PageHierarchy;
import database.Database;
import input.FilterInput;
import input.Input;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Filter;

public class App {
    private static App app = new App();
    private Page rootAuth;
    private Page rootUnauth;
    private User currUser;
    private Page currPage;
    private ArrayList<Movie> availableMovies;

    private App(){}

    public void start(Input input) {
        rootAuth = PageHierarchy.getPage("homepageAuth");
        rootUnauth = PageHierarchy.getPage("homepageUnauth");
        Database.getDatabase().updateDatabase(input);
        currUser = null;
        currPage = rootUnauth;
        //System.out.println(currPage);
        availableMovies = new ArrayList<>();
    }

    public static App getApp() {
        return app;
    }

    public Page getRootAuth() {
        return rootAuth;
    }

    public void setRootAuth(Page rootAuth) {
        this.rootAuth = rootAuth;
    }

    public Page getRootUnauth() {
        return rootUnauth;
    }

    public void setRootUnauth(Page rootUnauth) {
        this.rootUnauth = rootUnauth;
    }

    public User getCurrUser() {
        return currUser;
    }

    public void setCurrUser(User currUser) {
        this.currUser = currUser;
    }

    public Page getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Page currPage) {
        this.currPage = currPage;
    }

    public ArrayList<Movie> getAvailableMovies() {
        return availableMovies;
    }

    public void setAvailableMovies(ArrayList<Movie> availableMovies) {
        this.availableMovies = availableMovies;
    }

    public void goTo(Page page) {
//        for(Page child : currPage.getChildren()) {
//            if(page.equals(child))
//                currPage = child;
//        }
        // return erro, page not found
    }

    public void updateAvailableMovies() {
        if(availableMovies == null) {
            availableMovies = new ArrayList<>();
        } else {
            availableMovies.removeAll(availableMovies);
        }

        for(Movie movie : Database.getDatabase().getMovies()) {
            if(movie.getCountriesBanned().contains(currUser.getCredentials().getCountry()))
                continue;
            availableMovies.add(movie);
        }
    }

    public void updateAvailableMovies(String searchString) {
        if(availableMovies == null)
            availableMovies = new ArrayList<>();

        ArrayList<Movie> helper = new ArrayList<>();
        for(Movie movie : availableMovies) {
            if(movie.getName().startsWith(searchString)) {
                helper.add(movie);
            }
        }
        availableMovies = helper;
    }

    public void updateAvailableMovies(FilterInput filterInput) {
        if(availableMovies == null)
            availableMovies = new ArrayList<>();

        ArrayList<Movie> helper = new ArrayList<>();
        for(Movie movie : availableMovies) {
            if(movie.contains(filterInput.getContains())) {
                helper.add(movie);
            }
        }

        if(filterInput.getSort() == null) {
            availableMovies = helper;
            return;
        }

        helper.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                if (filterInput.getSort().getDuration() == null) {
                    if (filterInput.getSort().getRating().equals("decreasing")) {
                        return (int) ((o2.calculateRating() - o1.calculateRating()) * 100);
                    } else {
                        return (int) ((o1.calculateRating() - o2.calculateRating()) * 100);
                    }
                }

                if (o1.getDuration() - o2.getDuration() == 0) {
                    if (filterInput.getSort().getRating().equals("decreasing")) {
                        return (int) ((o1.calculateRating() - o2.calculateRating()) * 100);
                    } else {
                        return (int) ((o2.calculateRating() - o1.calculateRating()) * 100);
                    }
                }

                if (filterInput.getSort().getDuration().equals("decreasing")) {
                    return o1.getDuration() - o2.getDuration();
                } else {
                    return o2.getDuration() - o1.getDuration();
                }

            }
        });
        availableMovies = helper;
    }

}
