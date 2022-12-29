package database;

import app.Movie;
import app.User;
import input.Credentials;
import input.Input;
import input.MovieInput;
import input.UserInput;

import java.util.ArrayList;

public final class Database {
    public static final  Database DATABASE = new Database();
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private int movieListChanged;

    private final ArrayList<DatabaseObserver> observers  = new ArrayList<>(2);
    private Database() {
        observers.add(new AddingNotifier());
        observers.add(new DeletingNotifier());
    }

     public static Database getDatabase() {
        return DATABASE;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Updates the database with the given input
     * @param input The json input
     */
    public void updateDatabase(final Input input) {
        this.users = new ArrayList<>();
        this.movies = new ArrayList<>();

        for (UserInput userInput : input.getUsers()) {
            users.add(new User(userInput));
        }

        for (MovieInput movieInput : input.getMovies()) {
            movies.add(new Movie(movieInput));
        }
    }

    /**
     *
     * @param userName The name of the user being searched for
     * @return True if user exists in the database, false otherwise
     */
    public boolean userExists(final String userName) {
        for (User user : users) {
            if (user.getCredentials().getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registering a new user to database
     * @param credentials New user's credentials
     * @return New user's instance, null if this already exists
     */
    public User registerUser(final Credentials credentials) {
        if (userExists(credentials.getName())) {
            return null;
        }

        users.add(new User(credentials));

        return loginUser(credentials);
    }

    /**
     * Logging in an existent user
     * @param credentials Logging user's credentials
     * @return The logging user's instance, null if it does not exist, or the password is incorrect
     */
    public User loginUser(final Credentials credentials) {
        for (User user : users) {
            if (user.getCredentials().getName().equals(credentials.getName())) {
                if (user.getCredentials().getPassword().equals(credentials.getPassword())) {
                    return user;
                }
                return null;
            }
        }
        return null;
    }

    /**
     * Adds a new movie to the database, then notifies all the observers
     * @param addedMovie The movie added to the database
     * @return True if this operation is completed successfully, false otherwise
     * (movie name already exists)
     */
    public boolean addMovie(final Movie addedMovie) {
        for (Movie movie : movies) {
            if (movie.getName().equals(addedMovie.getName())) {
                return false;
            }
        }

        movies.add(addedMovie);

        movieListChanged = 1;

        notifyObservers(addedMovie);

        return true;
    }

    /**
     * Deletes a movie from the database, the notifies all the observers
     * @param deletedMovie The movie that is being deleted
     * @return True if success, false otherwise (movie does not exist)
     */
    public boolean deleteMovie(final String deletedMovie) {
        Movie helper = null;

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getName().equals(deletedMovie)) {
                helper = movies.remove(i);
                break;
            }
        }

        if (helper == null) {
            return false;
        }

        movieListChanged = -1;

        notifyObservers(helper);

        return true;
    }

    /**
     * Indicates if the movie database has changed (for observers)
     * @return 1 if a movie has been added,
     * -1 if a movie has been deleted,
     * 0 if no changes were made
     */
    public int movieListHasChanged() {
        return movieListChanged;
    }

    /**
     * Resets the changes indicator to 0 (method called by the
     * observers)
     */
    public void resetChangesCount() {
        movieListChanged = 0;
    }

    private void notifyObservers(final Movie movie) {
        for (DatabaseObserver observer : observers) {
            observer.update(movie);
        }
    }
}
