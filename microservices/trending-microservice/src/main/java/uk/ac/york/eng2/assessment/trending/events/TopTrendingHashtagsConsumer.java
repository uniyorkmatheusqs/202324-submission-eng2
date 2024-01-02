package uk.ac.york.eng2.assessment.trending.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import main.java.uk.ac.york.eng2.assessment.trending.events.ITopTrendingHashtagsConsumer;
import main.java.uk.ac.york.eng2.assessment.trending.events.TrendEventKey;
import uk.ac.york.eng2.assessment.trending.domain.HashtagRecord;
import uk.ac.york.eng2.assessment.trending.repositories.HashtagsRepository;

@KafkaListener(groupId = "hashtag-interaction")
public class TopTrendingHashtagsConsumer extends ITopTrendingHashtagsConsumer {

    @Inject
    private HashtagsRepository repo;

    @Override
    @Topic(ITopTrendingHashtagsConsumer.TOPIC_TOP_TRENDING_HASHTAGS)
    public void trend(@KafkaKey TrendEventKey trendEventKey, Long likes) {
        System.out.println("Got Here");
        var hashtagRecord = new HashtagRecord();
        
        hashtagRecord.setHashtag(trendEventKey.getHashtag());
        hashtagRecord.setStartMillis(trendEventKey.getStartMillis());
        hashtagRecord.setEndMillis(trendEventKey.getEndMillis());
        hashtagRecord.setLikes(likes);

        repo.save(hashtagRecord);
    }

}
