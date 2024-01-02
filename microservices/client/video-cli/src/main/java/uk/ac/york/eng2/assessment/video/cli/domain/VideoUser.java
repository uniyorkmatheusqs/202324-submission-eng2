package uk.ac.york.eng2.assessment.video.cli.domain;

public class VideoUser {
    private VideoUserKey id;

	private Video video;

    private User user;

    private Boolean viewed;
    
    private Boolean liked;

	public VideoUserKey getId() {
		return id;
	}

	public void setId(VideoUserKey id) {
		this.id = id;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean isView() {
		return viewed;
	}

	public void setView(Boolean view) {
		this.viewed = view;
	}

	public Boolean isLike() {
		return liked;
	}

	public void setLike(Boolean like) {
		this.liked = like;
	}

	@Override
	public String toString() {
		return "VideoUser [id=" + id + ", video=" + video + ", user=" + user + ", viewed=" + viewed + ", liked=" + liked
				+ "]";
	}
    
}
