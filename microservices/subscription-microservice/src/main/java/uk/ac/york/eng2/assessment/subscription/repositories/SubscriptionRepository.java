package uk.ac.york.eng2.assessment.subscription.repositories;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.subscription.domain.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    @Query("DELETE FROM subscription WHERE hashtag = :hashtag AND userId = :userId")
    void deleteOne(Subscription subscription);

    @Query(value = "SELECT * FROM subscription WHERE hashtag = :hashtag AND userId = :userId LIMIT 1", nativeQuery = true)
    Subscription listByUserId(String hashtag, Long userId);
}
