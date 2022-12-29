package app;

public final class Notification {
    private String movieName;
    private String message;
    public static final class Builder {
        private final String movieName;
        private final String message;
        public Builder(final String movieName, final String message) {
            this.message = message;
            this.movieName = movieName;
        }

        /**
         *
         * @return A Notification object containing this Builder's attributes
         */
        public Notification build() {
            return new Notification(this);
        }

    }

    private Notification(final Builder builder) {
        this.message = builder.message;
        this.movieName = builder.movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
