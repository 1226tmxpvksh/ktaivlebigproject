package ktaivlebigproject.infra;

import ktaivlebigproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AuthHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Auth>> {

    @Override
    public EntityModel<Auth> process(EntityModel<Auth> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/login")
                .withRel("login")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/logout")
                .withRel("logout")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/tokenreissue")
                .withRel("tokenreissue")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/tokenverification"
                )
                .withRel("tokenverification")
        );

        return model;
    }
}
