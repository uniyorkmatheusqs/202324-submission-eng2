package uk.ac.york.eng2.assessment.recommendation.events;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import jakarta.inject.Inject;
import main.java.uk.ac.york.eng2.assessment.recommendation.events.IHashtagsSubscriptionsConsumer;
import uk.ac.york.eng2.assessment.recommendation.domain.Hashtag;
import uk.ac.york.eng2.assessment.recommendation.domain.Subscription;
import uk.ac.york.eng2.assessment.recommendation.repositories.SubscriptionRepository;

@KafkaListener(groupId = "recommendation-subscription")
public class HashtagsSubscriptionsConsumer extends IHashtagsSubscriptionsConsumer {
    @Inject
    SubscriptionRepository repo;

    @Override
    public void subscribe(Long id, SubscriptionEventImpl subscriptionEvent) {
        var subscription = new Subscription();

        var hashtag = new Hashtag();
        hashtag.setValue(subscriptionEvent.getHashtag());

        subscription.setHashtag(hashtag);
        subscription.setActive(subscription.getActive());
        subscription.setUserId(subscription.getUserId());

        repo.save(subscription);
    }
    
}
