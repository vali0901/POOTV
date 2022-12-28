package database;

import app.Movie;

public interface DatabaseObserver {
    public void update(Movie movie);
}
