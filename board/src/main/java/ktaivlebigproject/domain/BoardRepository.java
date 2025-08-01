package ktaivlebigproject.domain;

import java.util.Date;
import java.util.List;
import ktaivlebigproject.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "boards", path = "boards")
public interface BoardRepository
    extends PagingAndSortingRepository<Board, Long> {}
