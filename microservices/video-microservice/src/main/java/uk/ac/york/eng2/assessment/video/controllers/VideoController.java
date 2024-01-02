package uk.ac.york.eng2.assessment.video.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import main.java.uk.ac.york.eng2.assessment.video.controllers.IVideoController;
import main.java.uk.ac.york.eng2.assessment.video.dtos.CreateVideoDTO;
import main.java.uk.ac.york.eng2.assessment.video.dtos.DislikeVideoDTO;
import main.java.uk.ac.york.eng2.assessment.video.dtos.LikeVideoDTO;
import main.java.uk.ac.york.eng2.assessment.video.dtos.WatchVideoDTO;
import main.java.uk.ac.york.eng2.assessment.video.events.VideoInteractionProducer;
import main.java.uk.ac.york.eng2.assessment.video.events.VideoPostProducer;
import uk.ac.york.eng2.assessment.video.domain.Hashtag;
import uk.ac.york.eng2.assessment.video.domain.User;
import uk.ac.york.eng2.assessment.video.domain.Video;
import uk.ac.york.eng2.assessment.video.domain.VideoInteraction;
import uk.ac.york.eng2.assessment.video.domain.VideoInteractionKey;
import uk.ac.york.eng2.assessment.video.events.InteractionEventImpl;
import uk.ac.york.eng2.assessment.video.events.InteractionEventKeyImpl;
import uk.ac.york.eng2.assessment.video.events.PostEventImpl;
import uk.ac.york.eng2.assessment.video.repositories.HashtagsRepository;
import uk.ac.york.eng2.assessment.video.repositories.UsersRepository;
import uk.ac.york.eng2.assessment.video.repositories.VideoInteractionRepository;
import uk.ac.york.eng2.assessment.video.repositories.VideosRepository;

@Controller("/videos")
public class VideoController implements IVideoController {
    @Inject
	private VideosRepository repo;

	@Inject
	private UsersRepository usersRepo;

	@Inject
	private HashtagsRepository hashtagsRepo;

	@Inject
	private VideoInteractionRepository videoInteractionRepo;

	@Inject
	private VideoPostProducer postProducer;

    @Inject
	private VideoInteractionProducer interactionProducer;

	private final String USER_REQUIRED_MESSAGE = "user required";
	private final String TITLE_REQUIRED_MESSAGE = "title required";
	private final String VIDEO_ID_REQUIRED_MESSAGE = "video id required";

	private final String USER_NOT_FOUND_MESSAGE = "user with id %d not found";
	private final String VIDEO_NOT_FOUND_MESSAGE = "video with id %d not found";

	@Override
	public List<Video> list() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'list'");
	}

	@Override
	@Transactional
	public Video listOne(Long id) {
		return this.repo.findById(id).orElse(null);
	}
	
    @Override
    @Transactional
    public HttpResponse<String> create(CreateVideoDTO createVideoDTO) {
        if (createVideoDTO.getUserId() == null) {
			return HttpResponse.badRequest(USER_REQUIRED_MESSAGE);
		}

		if (createVideoDTO.getTitle() == null || createVideoDTO.getTitle().isBlank()) {
			return HttpResponse.badRequest(TITLE_REQUIRED_MESSAGE);
		}

		User user = this.usersRepo.findById(createVideoDTO.getUserId())
				.orElse(null);

		if (user == null) {
			return HttpResponse.notFound(
					String.format(USER_NOT_FOUND_MESSAGE, createVideoDTO.getUserId()));
		}

		List<String> hashtagsParam = createVideoDTO.getHashtags();
		if (hashtagsParam == null) {
			hashtagsParam = new ArrayList<>();
		}

		Iterable<Hashtag> hashtagsFound = hashtagsRepo.saveAllValues(hashtagsParam);
		List<Hashtag> hashtags = toSet(hashtagsFound);

		Video video = new Video();
		video.setTitle(createVideoDTO.getTitle());
		video.setUser(user);
		video.setHashtags(hashtags);

		repo.save(video);

		postProducer.post(video.getId().toString(), new PostEventImpl(video));

		return HttpResponse.created(URI.create("/videos/" + video.getId()));
    }

    @Override
    @Transactional
    public HttpResponse<String> watch(Long id, WatchVideoDTO watchVideoDTO) {
        if (watchVideoDTO.getUserId() == null) {
			return HttpResponse.badRequest(USER_REQUIRED_MESSAGE);
		}

		if (id == null) {
			return HttpResponse.badRequest(VIDEO_ID_REQUIRED_MESSAGE);
		}

		Video video = this.repo.findById(id)
				.orElse(null);

		if (video == null) {
			return HttpResponse.notFound(String.format(VIDEO_NOT_FOUND_MESSAGE, id));
		}

		User user = this.usersRepo.findById(watchVideoDTO.getUserId())
				.orElse(null);

		if (user == null) {
			return HttpResponse.notFound(
					String.format(USER_NOT_FOUND_MESSAGE, watchVideoDTO.getUserId()));
		}

		VideoInteraction interaction = getVideoUser(video, user);

		if (interaction.isViewed() == null || !interaction.isViewed()) {
			interaction.setView(true);
			videoInteractionRepo.save(interaction);

			interactionProducer.interact(new InteractionEventKeyImpl(interaction.getId()), new InteractionEventImpl(interaction));
		}

		return HttpResponse.noContent();
    }

    @Override
    @Transactional
    public HttpResponse<String> like(Long id, LikeVideoDTO likeVideoDTO) {
        return setLike(id, likeVideoDTO.getUserId(), true);
    }

    @Override
    @Transactional
    public HttpResponse<String> dislike(Long id, DislikeVideoDTO dislikeVideoDTO) {
        return setLike(id, dislikeVideoDTO.getUserId(), false);
    }
    
    private HttpResponse<String> setLike(Long videoId, Long userId, Boolean like) {
		if (userId == null) {
			return HttpResponse.badRequest(USER_REQUIRED_MESSAGE);
		}

		if (videoId == null) {
			return HttpResponse.badRequest(VIDEO_ID_REQUIRED_MESSAGE);
		}

		Video video = this.repo.findById(videoId)
				.orElse(null);

		if (video == null) {
			return HttpResponse.notFound(String.format(VIDEO_NOT_FOUND_MESSAGE, videoId));
		}

		User user = this.usersRepo.findById(userId)
				.orElse(null);

		if (user == null) {
			return HttpResponse.notFound(
					String.format(USER_NOT_FOUND_MESSAGE, userId));
		}

		VideoInteraction interaction = getVideoUser(video, user);

		if (interaction.isLiked() == null || !interaction.isLiked().equals(like)) {
			interaction.setLike(like);
			videoInteractionRepo.save(interaction);

			interactionProducer.interact(new InteractionEventKeyImpl(interaction.getId()), new InteractionEventImpl(interaction));
		}

		return HttpResponse.noContent();
	}

	private <T> List<T> toSet(final Iterable<T> iterable) {
		final List<T> set = new ArrayList<>();
		for (Iterator<T> i = iterable.iterator(); i.hasNext();) {
			set.add(i.next());
		}
		return set;
	}

	private VideoInteraction getVideoUser(Video video, User user) {
		VideoInteractionKey key = new VideoInteractionKey(video.getId(), user.getId());

		VideoInteraction videoUser = videoInteractionRepo.findById(key)
				.orElse(null);

		if (videoUser == null) {
			videoUser = new VideoInteraction();
			videoUser.setId(key);
			videoUser.setUser(user);
			videoUser.setVideo(video);
		}

		return videoUser;
	}
}
