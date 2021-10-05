package co.com.appgate.consequences;

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
}
