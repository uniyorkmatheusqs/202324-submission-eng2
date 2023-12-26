package uk.ac.york.eng2.assessment.video.repositories;

import javax.persistence.EntityManager;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.video.domain.Video;
// import uk.ac.york.eng2.assessment.video.dto.VideoFilterDTO;

@Repository
public abstract class VideosRepository implements CrudRepository<Video, Long>{

	private final EntityManager entityManager;

    protected VideosRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
	// public List<Video> findByFilter(VideoFilterDTO filter) {
	// 	return entityManager
    //             .createQuery("FROM Video AS video WHERE video.user IN (:users) OR video.hashtags IN (:hashtags)", Video.class)
    //             .setParameter("users", filter.getUsers())
    //             .setParameter("hashtags", filter.getHashtags())
    //             .getResultList();
	// }
}
