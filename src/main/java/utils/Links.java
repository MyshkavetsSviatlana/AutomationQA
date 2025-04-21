package utils;

public enum Links {
    AUTOMATION_PRACTICE("http://www.automationpractice.pl/index.php"),
    ZOO("https://zoo.waw.pl/"),
    W3SCHOOLS("https://www.w3schools.com/"),
    CLICK_SPEED_TESTER("https://www.clickspeedtester.com/click-counter/"),
    ANDERSENLAB("https://andersenlab.com/");

    public final String link;

    Links(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public static int getSize() {
        int size = 0;
        for (Links link : Links.values()) {
            size += 1;
        }
        return size;
    }
}