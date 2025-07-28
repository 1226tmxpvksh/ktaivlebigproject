package ktaivlebigproject.infra;

import java.util.List;
import ktaivlebigproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "userReadModels",
    path = "userReadModels"
)
public interface UserReadModelRepository
    extends PagingAndSortingRepository<UserReadModel, Long> {}
