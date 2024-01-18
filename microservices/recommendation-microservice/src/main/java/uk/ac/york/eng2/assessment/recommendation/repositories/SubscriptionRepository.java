package uk.ac.york.eng2.assessment.recommendation.repositories;

import java.util.List;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.recommendation.domain.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    @Query(value = "SELECT h.value AS hashtag FROM subscription s INNER JOIN hashtag h on s.hashtag_id = h.id WHERE s.user_id IN ( SELECT user_id FROM subscription s INNER JOIN hashtag h on s.hashtag_id = h.id WHERE h.value = :hashtag) AND h.value <> hashtag ORDER BY s.created_at DESC LIMIT 10",
           nativeQuery = true)
    List<String> listHashtags(String hashtag);
}
