package output;

import java.util.ArrayList;

public final class ErrorOutput extends Output {
    public ErrorOutput() {
        this.setError("Error");
        this.setCurrentUser(null);
        this.setCurrentMoviesList(new ArrayList<>(0));
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
