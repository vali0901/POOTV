package output;

import java.util.ArrayList;

public class ErrorOutput extends Output{
    public ErrorOutput() {
        this.setError("Error");
        this.setCurrentUser(null);
        this.setCurrentMoviesList(new ArrayList<>(0));
    }
}
