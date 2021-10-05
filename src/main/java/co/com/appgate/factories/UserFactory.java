package co.com.appgate.factories;

import co.com.appgate.models.UserModel;
import org.openqa.selenium.NotFoundException;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static io.restassured.path.json.JsonPath.from;

public class UserFactory {

    private static final String USERS_JSON = "json/users.json";

    public static List<UserModel> getUsers() throws FileNotFoundException {
        return Arrays.asList(from(getActorsFile()).getObject("users", UserModel[].class));
    }

    public static UserModel send(int code) throws FileNotFoundException {
        return getUsers().stream()
                .filter(a -> a.getCode() == code)
                .findFirst()
                .orElseThrow(() ->
                        new NotFoundException(String.format("User with code %s not found", code)));
    }

    private static URL getActorsFile() throws FileNotFoundException {
        String path = USERS_JSON;
        URL filePath = UserFactory.class.getClassLoader().getResource(path);
        if (filePath == null) {
            throw new FileNotFoundException("File not found for users: " + path);
        }
        return filePath;
    }
}