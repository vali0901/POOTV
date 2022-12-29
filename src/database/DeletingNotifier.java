package database;

import app.Movie;
import app.Notification;
import app.User;

public final class DeletingNotifier implements DatabaseObserver {
    @Override
    public void update(final Movie deletedMovie) {
        if (Database.getDatabase().movieListHasChanged() >= 0) {
            return;
        }

        Database.getDatabase().resetChangesCount();

        for (User user : Database.getDatabase().getUsers()) {
            if (user.getPurchasedMovies().contains(deletedMovie)) {
                user.getNotifications().add(new Notification
                        .Builder(deletedMovie.getName(), "DELETE").build());
                user.getRefund(deletedMovie);
            }
        }

    }
}
