package uk.ac.york.eng2.assessment.video.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.video.domain.VideoInteraction;
import uk.ac.york.eng2.assessment.video.domain.VideoInteractionKey;

@Repository
public abstract class VideoInteractionRepository implements CrudRepository<VideoInteraction, VideoInteractionKey>{

}
