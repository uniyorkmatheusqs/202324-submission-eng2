package uk.ac.york.eng2.assessment.trending.events;

import io.micronaut.serde.annotation.Serdeable;
import main.java.uk.ac.york.eng2.assessment.trending.events.InteractionEvent;

@Serdeable
public class InteractionEventImpl extends InteractionEvent {
    private Boolean liked;

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

}
