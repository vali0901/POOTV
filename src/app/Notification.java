package app;

import org.w3c.dom.Node;

public class Notification {
    private String movieName;
    private String message;
    public static class Builder {
        private String movieName;
        private String message;
        public Builder (final String movieName, final String message) {
            this.message = message;
            this.movieName = movieName;
        }

        public Notification build() {
            return new Notification(this);
        }

    }

    private Notification(Builder builder) {
        this.message = builder.message;
        this.movieName = builder.movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
