package ktaivlebigproject.infra;

import ktaivlebigproject.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReportHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Report>> {

    @Override
    public EntityModel<Report> process(EntityModel<Report> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/reportdataupload"
                )
                .withRel("reportdataupload")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/reportgenerate")
                .withRel("reportgenerate")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/reportsave")
                .withRel("reportsave")
        );

        return model;
    }
}
