package uk.ac.york.eng2.assessment.video.controllers;

import java.net.URI;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import main.java.uk.ac.york.eng2.assessment.video.controllers.IUserController;
import main.java.uk.ac.york.eng2.assessment.video.dtos.CreateUserDTO;
import uk.ac.york.eng2.assessment.video.domain.User;
import uk.ac.york.eng2.assessment.video.repositories.UsersRepository;

@Controller("/users")
public class UserController implements IUserController {
    @Inject
	private UsersRepository repo;

	private final String NAME_REQUIRED_MESSAGE = "name required";

    @Override
    public HttpResponse<String> create(CreateUserDTO createUserDTO) {
        if (createUserDTO.getName() == null || createUserDTO.getName().isBlank()) {
			return HttpResponse.badRequest(NAME_REQUIRED_MESSAGE);
		}
		
		User user = new User();
		user.setName(createUserDTO.getName());
		
		repo.save(user);
		
		return HttpResponse.created(URI.create("/users/" + user.getId()));
    }

    @Override
    public HttpResponse<String> get(Long id) {
        // todo
        // return HttpResponse.ok(repo.findById(id).orElse(null));
        return null;
    }

}