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

    public int movieListHasChanged() {
        return movieListChanged;
    }

    public void resetChangesCount() {
        movieListChanged = 0;
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

    public boolean addMovie(Movie addedMovie) {
        for(Movie movie : movies)
            if(movie.getName().equals(addedMovie.getName()))
                return false;

        movies.add(addedMovie);

        movieListChanged = 1;

        for(DatabaseObserver observer : observers) {
            observer.update(addedMovie);
        }

        return true;
    }

    public boolean deleteMovie(String deletedMovie) {
        Movie helper = null;

        for(int i = 0; i < movies.size(); i++)
            if(movies.get(i).getName().equals(deletedMovie)){
                helper = movies.remove(i);
            }
        if(helper == null)
            return false;

        movieListChanged = -1;

        for(DatabaseObserver observer : observers) {
            observer.update(helper);
        }

        return true;
    }
}
