package output;

import app.App;
import app.Movie;

import java.util.ArrayList;

public final class LogOutput extends Output {
    public LogOutput() {
        setError(null);
        if (App.getApp().getAvailableMovies() != null) {
            setCurrentMoviesList(new ArrayList<>());
            for (final Movie movie : App.getApp().getAvailableMovies()) {
                MovieOutput helper = new MovieOutput(movie);
                this.getCurrentMoviesList().add(helper);
            }
        } else {
            this.setCurrentMoviesList(new ArrayList<>(0));
        }

        this.setCurrentUser(new UserOutput(App.getApp().getCurrUser()));
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
