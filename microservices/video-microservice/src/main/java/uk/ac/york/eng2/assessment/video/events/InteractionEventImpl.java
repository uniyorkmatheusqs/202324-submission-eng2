package uk.ac.york.eng2.assessment.video.events;

import main.java.uk.ac.york.eng2.assessment.video.events.InteractionEvent;
import uk.ac.york.eng2.assessment.video.domain.VideoInteraction;

public class InteractionEventImpl extends InteractionEvent {
    public InteractionEventImpl(VideoInteraction interaction) {
        this.setVideoId(interaction.getVideo().getId());
        this.setUserId(interaction.getUser().getId());
        this.setViewed(interaction.isViewed());
        this.setLiked(interaction.isLiked());
    }
}
