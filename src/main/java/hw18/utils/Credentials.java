package hw18.utils;

public enum Credentials {
    REGISTERED_USER_LOGIN("john.doe@gmail.com"),
    REGISTERED_USER_PASSWORD("Qwerty123"),
    LOGIN_MID_SPACES("john doe@gmail.com");
    public final String credential;
    Credentials(String credential) {
        this.credential = credential;
    }
    public String getCredential() {
        return credential;
    }
}
