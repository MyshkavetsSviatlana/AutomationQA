package utils;

public enum Urls {
    LOGIN_PAGE("https://qa-course-01.andersenlab.com/login"),
    REGISTRATION_PAGE("https://qa-course-01.andersenlab.com/registration");

    public final String url;

    Urls(String url) {
        this.url = url;
    }

    public String getLink() {
        return url;
    }

    public static int getSize() {
        int size = 0;
        for (Urls url : Urls.values()) {
            size += 1;
        }
        return size;
    }
}
