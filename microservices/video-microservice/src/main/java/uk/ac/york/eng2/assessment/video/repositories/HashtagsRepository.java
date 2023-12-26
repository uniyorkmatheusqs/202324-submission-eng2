package uk.ac.york.eng2.assessment.video.repositories;

import java.util.ArrayList;
import java.util.List;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.video.domain.Hashtag;

@Repository
public abstract class HashtagsRepository implements CrudRepository<Hashtag, Long>{    
	public Iterable<Hashtag> saveAllValues(List<String> hashtagsValues) {
		List<Hashtag> hashtags = new ArrayList<>();
		
		for (String value : hashtagsValues) {
		  Hashtag hashtag = new Hashtag();
		  hashtag.setValue(value);
		  
		  hashtags.add(hashtag);
		}

		return this.saveAll(hashtags);
	}
}
