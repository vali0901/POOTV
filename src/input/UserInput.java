package input;

public class UserInput {
    private Credentials credentials;

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "credentials=" + credentials +
                '}';
    }
}
