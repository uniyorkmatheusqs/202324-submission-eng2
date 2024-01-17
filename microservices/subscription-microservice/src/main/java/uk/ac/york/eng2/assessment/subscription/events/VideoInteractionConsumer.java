package uk.ac.york.eng2.assessment.subscription.events;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import jakarta.inject.Inject;
import main.java.uk.ac.york.eng2.assessment.subscription.events.IVideoInteractionConsumer;
import main.java.uk.ac.york.eng2.assessment.subscription.events.InteractionEventKey;
import uk.ac.york.eng2.assessment.subscription.clients.VideoClient;
import uk.ac.york.eng2.assessment.subscription.repositories.VideoRepository;

@KafkaListener(groupId = "subscription-video-interaction")
public class VideoInteractionConsumer extends IVideoInteractionConsumer {

    @Inject
    private VideoRepository repo;

    @Inject
	private VideoClient client;

    @Override
    public void interact(InteractionEventKey interactionEventKey, InteractionEventImpl interactionEvent) {
        var video = client.listOne(interactionEvent.getVideoId());
        repo.save(video);
    }
    
}
