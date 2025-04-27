package hw15.utils;

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

    // You can remove the method getSize from your enum classes and use, for example:
    //int numberOfLinks = Links.values().length;
    public static int getSize() {
        int size = 0;
        for (Urls url : Urls.values()) {
            size += 1;
        }
        return size;
    }
}
