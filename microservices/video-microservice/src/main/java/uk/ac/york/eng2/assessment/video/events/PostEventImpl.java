package uk.ac.york.eng2.assessment.video.events;

import main.java.uk.ac.york.eng2.assessment.video.events.PostEvent;
import uk.ac.york.eng2.assessment.video.domain.Video;

public class PostEventImpl extends PostEvent {
    public PostEventImpl(Video video) {
        this.setTitle(video.getTitle());
        this.setUserId(video.getUser().getId());
        this.setId(video.getId());
    }
}
