package ktaivlebigproject.infra;

import ktaivlebigproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class BoardHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Board>> {

    @Override
    public EntityModel<Board> process(EntityModel<Board> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/postcreate")
                .withRel("postcreate")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/postdelete")
                .withRel("postdelete")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/downloadfile")
                .withRel("downloadfile")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/postupdate")
                .withRel("postupdate")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/postdetail")
                .withRel("postdetail")
        );
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/viewcountincrease"
                )
                .withRel("viewcountincrease")
        );

        return model;
    }
}
