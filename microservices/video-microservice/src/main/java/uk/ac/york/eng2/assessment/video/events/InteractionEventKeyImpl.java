package uk.ac.york.eng2.assessment.video.events;

import main.java.uk.ac.york.eng2.assessment.video.events.InteractionEventKey;
import uk.ac.york.eng2.assessment.video.domain.VideoInteractionKey;

public class InteractionEventKeyImpl extends InteractionEventKey {
    public InteractionEventKeyImpl(VideoInteractionKey key) {
        this.setUserId(key.getUserId().toString());
        this.setVideoId(key.getVideoId().toString());
    }
}
