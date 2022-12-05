package database;

import app.Movie;
import app.User;
import input.Credentials;
import input.Input;
import input.MovieInput;
import input.UserInput;

import java.util.ArrayList;

public class Database {
    public static Database database = new Database();
    private ArrayList<User> users;
    private ArrayList<Movie> movies;
    private Database() {}

    public static Database getDatabase() {
        return database;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public void updateDatabase(Input input) {
        this.users = new ArrayList<>();
        this.movies = new ArrayList<>();

        for(UserInput userInput : input.getUsers()) {
            users.add(new User(userInput));
        }

        for(MovieInput movieInput : input.getMovies()) {
            movies.add(new Movie(movieInput));
        }
    }

    public boolean userExists(String userName) {
        for(User user : users) {
            if(user.getCredentials().getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }
    public User registerUser (Credentials credentials) {
        if(userExists(credentials.getName()))
            return null;
        users.add(new User(credentials));

        return loginUser(credentials);
    }

    public User loginUser (Credentials credentials) {
        for(User user : users) {
            if(user.getCredentials().getName().equals(credentials.getName())) {
                if(user.getCredentials().getPassword().equals(credentials.getPassword())) {
                    return user;
                }
                return null;
            }
        }
        return null;
    }
}
