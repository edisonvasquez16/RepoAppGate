package co.com.appgate.enpoints;

public enum User {

    GET_USER_INFO("/timezoneJSON");

    private final String path;

    User(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
