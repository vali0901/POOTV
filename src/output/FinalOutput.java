package output;

import app.App;

import java.util.ArrayList;

public class FinalOutput extends Output{
    public FinalOutput() {
        setError(null);
        setCurrentMoviesList(null);
        setCurrentUser(new UserOutput(App.getApp().getCurrUser()));
    }
}
