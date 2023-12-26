package uk.ac.york.eng2.assessment.video.repositories;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import uk.ac.york.eng2.assessment.video.domain.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long>{

}
