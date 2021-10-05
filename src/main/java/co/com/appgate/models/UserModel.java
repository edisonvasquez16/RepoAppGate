package co.com.appgate.models;

import lombok.Data;

@Data
public class UserModel {

    private int code;
    private String formatted;
    private String lat;
    private String lng;
    private String username;
    private String style;

}
