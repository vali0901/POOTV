package database;

import app.Movie;

public interface DatabaseObserver {
    /**
     * The main method of this observer
     * In this context, it is used for notifying users depending on the changes
     * made to the movie database
     * @param movie The movie that has been added / deleted from movie database
     */
    void update(Movie movie);
}
