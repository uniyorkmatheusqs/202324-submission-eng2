package uk.ac.york.eng2.assessment.trending.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.trending.domain.HashtagRecord;

@Repository
public interface HashtagsRepository extends CrudRepository<HashtagRecord, Long> {
    
}
