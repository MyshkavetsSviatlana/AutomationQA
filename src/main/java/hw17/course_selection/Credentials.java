package hw17.course_selection;

public enum Credentials {
    REGISTERED_USER_LOGIN("john.doe@gmail.com"),
    REGISTERED_USER_PASSWORD("Qwerty123");
    public final String credential;
    Credentials (String credential) {
        this.credential = credential;
    }
    public String getCredential() {
        return credential;
    }
}
