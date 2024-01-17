package uk.ac.york.eng2.assessment.subscription.events;

import io.micronaut.serde.annotation.Serdeable;
import main.java.uk.ac.york.eng2.assessment.subscription.events.InteractionEvent;

@Serdeable
public class InteractionEventImpl extends InteractionEvent {
    private Long videoId;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

}
