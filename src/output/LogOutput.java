package output;

import app.App;
import app.Movie;

import java.util.ArrayList;

public class LogOutput extends Output{
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
}
