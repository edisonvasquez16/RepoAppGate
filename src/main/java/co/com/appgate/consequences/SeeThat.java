package co.com.appgate.consequences;

import co.com.appgate.enums.Messages;
import co.com.appgate.models.UserResponseModel;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.core.IsEqual.equalTo;

public class SeeThat {

    public static Consequence[] userInfo() {
        return new Consequence[]{
                seeThat("The response code", response -> LastResponse.received()
                                .answeredBy(theActorInTheSpotlight())
                                .statusCode(),
                        equalTo(200)),
                seeThat("Schema validation", response -> LastResponse.received()
                                .answeredBy(theActorInTheSpotlight()).asString(),
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/user-schema.json"))
        };
    }

    public static Consequence[] userInfoFail(int status) {
        return new Consequence[]{
                seeThat("The response code", response -> LastResponse.received()
                                .answeredBy(theActorInTheSpotlight())
                                .statusCode(),
                        equalTo(getStatusResponse(status))),
                seeThat("Schema validation", response -> LastResponse.received()
                                .answeredBy(theActorInTheSpotlight()).asString(),
                        JsonSchemaValidator
                                .matchesJsonSchemaInClasspath("schemas/user-schema-fail.json")),
                seeThat("Message validation", response -> LastResponse.received()
                                .answeredBy(theActorInTheSpotlight()).as(UserResponseModel.class).getStatus().getMessage(),
                        equalTo(getMessage(status))),
                seeThat("Value response validation", response -> LastResponse.received()
                                .answeredBy(theActorInTheSpotlight()).as(UserResponseModel.class).getStatus().getValue(),
                        equalTo(getValue(status)))
        };
    }

    private static String getMessage(int code) {
        String message = "";
        switch (code) {
            case 401:
                message = Messages.INVALID_USER.message();
                break;
            case 402:
            case 403:
                message = Messages.MISSING_PARAMETER.message();
                break;
            case 406:
                message = Messages.ADD_USERNAME.message();
                break;
            default:
                message = "";
                break;

        }
        return message;
    }

    private static int getValue(int code) {
        int value = 0;
        switch (code) {
            case 401:
                value = Messages.INVALID_USER.code();
                break;
            case 402:
            case 403:
                value = Messages.MISSING_PARAMETER.code();
                break;
            case 406:
                value = Messages.ADD_USERNAME.code();
                break;
            default:
                value = 0;
                break;
        }
        return value;
    }

    private static int getStatusResponse(int code) {
        int status = 0;
        switch (code) {
            case 401:
            case 406:
                status = 401;
                break;
            case 402:
            case 403:
                status = 200;
                break;
            default:
                status = 0;
                break;
        }
        return status;
    }
}
