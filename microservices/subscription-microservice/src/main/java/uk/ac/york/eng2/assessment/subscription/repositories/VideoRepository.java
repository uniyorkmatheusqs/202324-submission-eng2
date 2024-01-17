package uk.ac.york.eng2.assessment.subscription.repositories;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.subscription.domain.Video;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {
     @Query(value = "SELECT v.* FROM video v JOIN video_hashtag vh ON v.video_id = vh.videos_id JOIN hashtag h ON vh.hashtags_id = h.id WHERE h.value = :hashtag ORDER BY v.created_at DESC LIMIT 10", 
           nativeQuery = true)
    List<Video> listNextVideos(@NonNull @NotNull String hashtag);   
}
