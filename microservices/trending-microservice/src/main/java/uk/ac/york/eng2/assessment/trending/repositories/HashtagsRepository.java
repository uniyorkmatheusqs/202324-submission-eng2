package uk.ac.york.eng2.assessment.trending.repositories;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.trending.domain.HashtagRecord;

@Repository
public interface HashtagsRepository extends CrudRepository<HashtagRecord, Long> {
    @Query(value = "SELECT DISTINCT hashtag FROM (SELECT * FROM hashtag_record WHERE start_millis >= :limitDate ORDER BY likes DESC LIMIT 10) AS q", 
           nativeQuery = true)
    List<String> listTopHashtags(@NonNull @NotNull Long limitDate);   
}
