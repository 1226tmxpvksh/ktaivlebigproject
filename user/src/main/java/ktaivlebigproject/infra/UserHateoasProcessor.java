package ktaivlebigproject.infra;

import ktaivlebigproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<User>> {

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/userregister")
                .withRel("userregister")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/userdelete")
                .withRel("userdelete")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/userprofileupdate"
                )
                .withRel("userprofileupdate")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/getuserinfo")
                .withRel("getuserinfo")
        );

        return model;
    }
}
