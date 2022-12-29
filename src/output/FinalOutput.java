package output;

import app.App;

import java.util.ArrayList;

public final class FinalOutput extends Output {
    public FinalOutput() {
        setError(null);
        setCurrentMoviesList(null);
        setCurrentUser(new UserOutput(App.getApp().getCurrUser()));
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
