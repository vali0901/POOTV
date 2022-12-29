package database;

import app.Movie;
import app.Notification;
import app.User;

public final class AddingNotifier implements DatabaseObserver {
    @Override
    public void update(final Movie addedMovie) {
        if (Database.getDatabase().movieListHasChanged() <= 0) {
            return;
        }

        Database.getDatabase().resetChangesCount();

        for (User user : Database.getDatabase().getUsers()) {
            if (user.isSubscribed(addedMovie.getGenres())
                    && !addedMovie.getCountriesBanned()
                    .contains(user.getCredentials().getCountry())) {
                user.getNotifications().add(new Notification
                        .Builder(addedMovie.getName(), "ADD").build());
            }
        }
    }
}
