package uk.ac.york.eng2.assessment.trending.repositories;

import java.util.List;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.trending.domain.HashtagRecord;

@Repository
public interface HashtagsRepository extends CrudRepository<HashtagRecord, Long> {
    @Query("SELECT DISTINCT hashtag FROM hashtag_record WHERE FROM_UNIXTIME(start_millis / 1000) >= CURDATE() - INTERVAL 1 HOUR ORDER BY likes DESC LIMIT 10")
    List<String> listTopHashtags();   
}
