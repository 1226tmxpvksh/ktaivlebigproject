package ktaivlebigproject.infra;

import java.util.List;
import ktaivlebigproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "reportReadModels",
    path = "reportReadModels"
)
public interface ReportReadModelRepository
    extends PagingAndSortingRepository<ReportReadModel, Long> {}
