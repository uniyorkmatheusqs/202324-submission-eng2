package test.java.uk.ac.york.eng2.assessment.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.video.repositories.UsersRepository;
import uk.ac.york.eng2.assessment.videos.client.UsersClient;
import uk.ac.york.eng2.assessment.videos.dto.UserDetailsDTO;

@MicronautTest(transactional = false, environments = "no_streams")
public class UserControllerTest {
    
    @Inject
	UsersClient client;

	@Inject
	UsersRepository repo;

    @BeforeEach
	public void clean() {
		repo.deleteAll();
	}

    @Nested
    @DisplayName("Should be able to create a user")
    class AddUserTests {
        @Test
        void addUser() {
            final String name = "Joe Doe";

            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setName(name);

            HttpResponse<Void> response = client.create(userDetails);
            assertEquals(HttpStatus.CREATED, response.getStatus(), "User creation should be successful");
        }

        @Test
        void addUserWithoutName() {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setName("");
            
            try {
                client.create(userDetails);
                fail("expected bad request but got success request");
            } catch (HttpClientResponseException ex) {
                assertEquals(HttpStatus.BAD_REQUEST.getCode(), ex.getResponse().code(),
                        "error must be bad request");
                // assertEquals("name required", ex.getResponse().body(),
                //         "should not be able to create a user without name");
            }
        }
    }
}
