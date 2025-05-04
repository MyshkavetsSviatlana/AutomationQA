package hw18.utils;

public enum Urls {
    LOGIN_PAGE("https://qa-course-01.andersenlab.com/login"),
    ACCOUNT_PAGE("https://qa-course-01.andersenlab.com/"),
    REGISTRATION_PAGE("https://qa-course-01.andersenlab.com/registration");

    public final String url;

    Urls(String url) {
        this.url = url;
    }

    public String getLink() {
        return url;
    }
}
