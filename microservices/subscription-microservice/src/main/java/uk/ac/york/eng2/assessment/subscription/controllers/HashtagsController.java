package uk.ac.york.eng2.assessment.subscription.controllers;

import java.net.URI;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import main.java.uk.ac.york.eng2.assessment.subscription.controllers.IHashtagsController;
import main.java.uk.ac.york.eng2.assessment.subscription.dtos.ListHashtagsDTO;
import main.java.uk.ac.york.eng2.assessment.subscription.events.HashtagsSubscriptionsProducer;
import main.java.uk.ac.york.eng2.assessment.subscription.events.SubscriptionEvent;
import uk.ac.york.eng2.assessment.subscription.domain.Subscription;
import uk.ac.york.eng2.assessment.subscription.repositories.SubscriptionRepository;
import uk.ac.york.eng2.assessment.subscription.repositories.VideoRepository;

@Controller(IHashtagsController.BASE_URI)
public class HashtagsController implements IHashtagsController {

    @Inject
    SubscriptionRepository subRepo;

    @Inject
    VideoRepository videoRepo;

    @Inject
    HashtagsSubscriptionsProducer producer;

    @Override
    public HttpResponse<String> subscribe(String hashtag, Long userId) {
        Subscription subscription = new Subscription();
        subscription.setHashtag(hashtag);
        subscription.setUserId(userId);

        subRepo.save(subscription);

        SubscriptionEvent event = new SubscriptionEvent();

        event.setId(subscription.getId());
        event.setHashtag(subscription.getHashtag());
        event.setUserId(subscription.getUserId());
        event.setActive(true);

        producer.subscribe(subscription.getId(), event);
        
		return HttpResponse.created(URI.create(IHashtagsController.BASE_URI + "/" + subscription.getId()));
    }

    @Override
    public HttpResponse<String> unsubscribe(String hashtag, Long userId) {
        Subscription subscription = new Subscription();
        subscription.setHashtag(hashtag);
        subscription.setUserId(userId);

        subRepo.deleteOne(subscription);

        SubscriptionEvent event = new SubscriptionEvent();

        event.setId(subscription.getId());
        event.setHashtag(subscription.getHashtag());
        event.setUserId(subscription.getUserId());
        event.setActive(false);

        producer.subscribe(subscription.getId(), event);

        return HttpResponse.noContent();
    }

    @Override
    public HttpResponse<Object> list(ListHashtagsDTO listHashtagsDTO) {
        var sub = subRepo.listByUserId(listHashtagsDTO.getHashtag(), listHashtagsDTO.getUserId());
        if (sub == null || sub.getId() == null) {
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(videoRepo.listNextVideos(listHashtagsDTO.getHashtag()));
    }
    
}
