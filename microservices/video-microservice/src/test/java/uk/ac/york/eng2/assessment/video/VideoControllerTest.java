package test.java.uk.ac.york.eng2.assessment.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import uk.ac.york.eng2.assessment.video.repositories.HashtagsRepository;
import uk.ac.york.eng2.assessment.video.repositories.UsersRepository;
import uk.ac.york.eng2.assessment.video.repositories.VideoInteractionRepository;
import uk.ac.york.eng2.assessment.video.repositories.VideosRepository;
import uk.ac.york.eng2.assessment.videos.client.UsersClient;
import uk.ac.york.eng2.assessment.videos.client.VideosClient;
import uk.ac.york.eng2.assessment.videos.dto.LikeVideoDTO;
import uk.ac.york.eng2.assessment.videos.dto.UserDetailsDTO;
import uk.ac.york.eng2.assessment.videos.dto.VideoDetailsDTO;
import uk.ac.york.eng2.assessment.videos.dto.WatchVideoDTO;

@MicronautTest(transactional = false, environments = "no_streams")
class VideoControllerTest {

    @Inject
    VideosClient client;

    @Inject
    UsersClient usersClient;

    @Inject
    VideosRepository repo;

    @Inject
    UsersRepository userRepo;

    @Inject
    HashtagsRepository hashtagRepo;

    @Inject
    VideoInteractionRepository videoInteractionRepo;

    @Nested
    @DisplayName("Should be able to create a video")
    class AddVideoTests {

        @BeforeEach()
        public void clean() {
            // hashtagRepo.deleteAll();
            videoInteractionRepo.deleteAll();
            repo.deleteAll();
            userRepo.deleteAll();
        }

        @Test
        void addVideo() {
            final String videoTitle = "Microservices in Java: Zero to Hero!";

            UserDetailsDTO userDTO = new UserDetailsDTO();
            userDTO.setName("Joe Doe");
            HttpResponse<Void> userResponse = usersClient.create(userDTO);

            String uri = userResponse.getHeaders().findFirst(HttpHeaders.LOCATION).orElse(null);
            String id = uri.split("/")[2];

            final Long userId = Long.parseLong(id);

            VideoDetailsDTO video = new VideoDetailsDTO();
            video.setTitle(videoTitle);
            video.setUserId(userId);
            HttpResponse<String> response = client.create(video);
            assertEquals(HttpStatus.CREATED, response.getStatus(), "video creation should be successful");
        }

        @Test
        void addVideoWithoutUser() {
            final String videoTitle = "Microservices in Java: Zero to Hero!";

            VideoDetailsDTO video = new VideoDetailsDTO();
            video.setTitle(videoTitle);

            try {
                client.create(video);
                fail("expected bad request but got success request");

            } catch (HttpClientResponseException ex) {
                assertEquals(HttpStatus.BAD_REQUEST.getCode(), ex.getResponse().code(),
                        "error must be bad request");
                assertEquals("user required", ex.getResponse().body(),
                        "should not be able to create a video without user");
            }
        }

        @Test
        void addVideoWithoutTitle() {
            final long userId = 1;

            VideoDetailsDTO video = new VideoDetailsDTO();
            video.setUserId(userId);

            try {
                client.create(video);
                fail("expected bad request but got success request");
            } catch (HttpClientResponseException ex) {
                assertEquals(HttpStatus.BAD_REQUEST.getCode(), ex.getResponse().code(),
                        "error must be bad request");
                assertEquals("title required", ex.getResponse().body(),
                        "should not be able to create a video without title");
            }
        }
    }

    @Nested
    @DisplayName("Should be able to watch a video")
    class WatchVideoTests {

        Long videoId;
        Long userId;

        @BeforeEach()
        public void clean() {
            videoInteractionRepo.deleteAll();

            UserDetailsDTO userDTO = new UserDetailsDTO();
            userDTO.setName("Joe Doe");
            HttpResponse<Void> userResponse = usersClient.create(userDTO);

            String uri = userResponse.getHeaders().findFirst(HttpHeaders.LOCATION).orElse(null);
            userId = Long.parseLong(uri.split("/")[2]);

            VideoDetailsDTO video = new VideoDetailsDTO();
            video.setTitle("Any Title");
            video.setUserId(userId);
            HttpResponse<String> videoResponse = client.create(video);

            uri = videoResponse.getHeaders().findFirst(HttpHeaders.LOCATION).orElse(null);
            videoId = Long.parseLong(uri.split("/")[2]);
        }

        @Test
        void watchVideo() {
            WatchVideoDTO watchVideoDTO = new WatchVideoDTO();
            watchVideoDTO.setUserId(userId);

            HttpResponse<String> response = client.watch(videoId, watchVideoDTO);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatus(), "Watching a video should be successful");
        }

        @Test
        void watchVideoWithoutUser() {
            WatchVideoDTO watchVideoDTO = new WatchVideoDTO();

            try {
                client.watch(videoId, watchVideoDTO);
                fail("expected bad request but got success request");
            } catch (HttpClientResponseException ex) {
                assertEquals(HttpStatus.BAD_REQUEST.getCode(), ex.getResponse().code(),
                        "error must be bad request");
                assertEquals("user required", ex.getResponse().body(),
                        "should not be able to watch a video without user");
            }
        }
    }

    @Nested
    @DisplayName("Should be able to like a video")
    class LikeVideoTests {
        Long videoId;
        Long userId;

        @BeforeEach()
        public void clean() {
            videoInteractionRepo.deleteAll();

            UserDetailsDTO userDTO = new UserDetailsDTO();
            userDTO.setName("Joe Doe");
            HttpResponse<Void> userResponse = usersClient.create(userDTO);

            String uri = userResponse.getHeaders().findFirst(HttpHeaders.LOCATION).orElse(null);
            userId = Long.parseLong(uri.split("/")[2]);

            VideoDetailsDTO video = new VideoDetailsDTO();
            video.setTitle("Any Title");
            video.setUserId(userId);
            HttpResponse<String> videoResponse = client.create(video);

            uri = videoResponse.getHeaders().findFirst(HttpHeaders.LOCATION).orElse(null);
            videoId = Long.parseLong(uri.split("/")[2]);
        }

        @Test
        void likeVideo() {
            LikeVideoDTO likeVideoDTO = new LikeVideoDTO();
            likeVideoDTO.setUserId(userId);

            HttpResponse<String> response = client.like(videoId, likeVideoDTO);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatus(), "Liking a video should be successful");
        }

        @Test
        void likeVideoWithoutUser() {
            LikeVideoDTO likeVideoDTO = new LikeVideoDTO();

            try {
                client.like(videoId, likeVideoDTO);
                fail("expected bad request but got success request");
            } catch (HttpClientResponseException ex) {
                assertEquals(HttpStatus.BAD_REQUEST.getCode(), ex.getResponse().code(),
                        "error must be bad request");
                assertEquals("user required", ex.getResponse().body(),
                        "should not be able to like a video without user");
            }
        }
    }

    @Nested
    @DisplayName("Should be able to dislike a video")
    class DislikeVideoTests {
        Long videoId;
        Long userId;

        @BeforeEach()
        public void clean() {
            videoInteractionRepo.deleteAll();

            UserDetailsDTO userDTO = new UserDetailsDTO();
            userDTO.setName("Joe Doe");
            HttpResponse<Void> userResponse = usersClient.create(userDTO);

            String uri = userResponse.getHeaders().findFirst(HttpHeaders.LOCATION).orElse(null);
            userId = Long.parseLong(uri.split("/")[2]);

            VideoDetailsDTO video = new VideoDetailsDTO();
            video.setTitle("Any Title");
            video.setUserId(userId);
            HttpResponse<String> videoResponse = client.create(video);

            uri = videoResponse.getHeaders().findFirst(HttpHeaders.LOCATION).orElse(null);
            videoId = Long.parseLong(uri.split("/")[2]);
        }

        @Test
        void dislikeVideo() {
            LikeVideoDTO likeVideoDTO = new LikeVideoDTO();
            likeVideoDTO.setUserId(userId);

            HttpResponse<String> response = client.dislike(videoId, likeVideoDTO);
            assertEquals(HttpStatus.NO_CONTENT, response.getStatus(), "Disliking a video should be successful");
        }

        @Test
        void dislikeVideoWithoutUser() {
            LikeVideoDTO likeVideoDTO = new LikeVideoDTO();

            try {
                client.dislike(videoId, likeVideoDTO);
                fail("expected bad request but got success request");
            } catch (HttpClientResponseException ex) {
                assertEquals(HttpStatus.BAD_REQUEST.getCode(), ex.getResponse().code(),
                        "error must be bad request");
                assertEquals("user required", ex.getResponse().body(),
                        "should not be able to like a video without user");
            }
        }
    }
}
