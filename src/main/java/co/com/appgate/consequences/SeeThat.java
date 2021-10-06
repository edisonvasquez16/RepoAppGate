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
        String lr = LastResponse.received().answeredBy(theActorInTheSpotlight()).asString();
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
                        equalTo(status)),
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

    public static Consequence[] userInfoFailWith(int status) {
        return new Consequence[]{
                seeThat("The response code", response -> LastResponse.received()
                                .answeredBy(theActorInTheSpotlight())
                                .statusCode(),
                        equalTo(200)),
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
        String messaje = "";
        switch (code) {
            case 401:
                messaje = Messages.INVALID_USER.message();
                break;
            case 402:
            case 403:
                messaje = Messages.MISSING_PARAMETER.message();
                break;
            default:
                messaje = "";
                break;

        }
        return messaje;
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
            default:
                value = 0;
                break;
        }
        return value;
    }
}
