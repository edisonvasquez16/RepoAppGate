package co.com.appgate.enums;

public enum Messages {

    INVALID_USER("invalid user",10),
    MISSING_PARAMETER("missing parameter ",14),
    ADD_USERNAME("Please add a username to each call in order for geonames to be able to identify the calling application and count the credits usage.",10);

    private final String message;
    private final int code;

    Messages(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String message() {
        return message;
    }
    public int code() {
        return code;
    }

}
