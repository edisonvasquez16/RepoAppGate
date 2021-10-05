package co.com.appgate.features;

import co.com.appgate.conf.SetTheStage;
import co.com.appgate.consequences.SeeThat;
import co.com.appgate.factories.UserFactory;
import co.com.appgate.tasks.GetUser;
import lombok.SneakyThrows;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Narrative;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;


import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

@RunWith(SerenityRunner.class)
@Narrative(text = {"In app example apis",
        "As a user",
        "I want to validate the response of user info"})
@WithTag("MsUserInfo")
public class ExampleTest extends SetTheStage {

    @SneakyThrows
    @Test
    @WithTags({@WithTag("Smoke")})
    public void getUserInfoOk() {
        theActorInTheSpotlight().wasAbleTo(
                GetUser.information(
                        UserFactory.send(200)
                ));
        theActorInTheSpotlight()
                .should(SeeThat.userInfo());
    }
}
