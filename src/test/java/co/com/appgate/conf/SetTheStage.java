package co.com.appgate.conf;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Before;
import java.util.ArrayList;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class SetTheStage {

    @Before
    public void setStage() {
        OnStage.setTheStage(Cast.whereEveryoneCan(CallAnApi.at("http://api.geonames.org")));
        SerenityRest.setDefaultRequestSpecification(defaultRequestSpecification());
        theActorCalled("User AppGate");
    }


    public RequestSpecification defaultRequestSpecification() {
        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());
        return new RequestSpecBuilder().addFilters(filters).build();
    }
}
