package co.com.appgate.tasks;

import co.com.appgate.enpoints.User;
import co.com.appgate.models.UserModel;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUserFail implements Task {

    private final UserModel user;

    public GetUserFail(UserModel user){
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(User.GET_USER_INFO.path())
                        .with(request -> request
                                .param("formatted",user.getFormatted())
                                .param("lat",user.getLat())
                                .param("lng",user.getLng())
                                .param("style",user.getStyle())
                        )
        );
    }

    public static GetUserFail information(UserModel user) {
        return instrumented(GetUserFail.class, user);
    }
}
