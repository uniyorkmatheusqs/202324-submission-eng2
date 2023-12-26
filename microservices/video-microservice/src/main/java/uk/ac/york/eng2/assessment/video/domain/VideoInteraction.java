package uk.ac.york.eng2.assessment.video.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import io.micronaut.serde.annotation.Serdeable;

@Entity
@Serdeable
public class VideoInteraction {
	
	@EmbeddedId
    private VideoInteractionKey id;

    @MapsId("videoId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Video video;

    @MapsId("userId")
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @Column(columnDefinition = "boolean default false")
    private Boolean viewed;
    
    @Column(columnDefinition = "boolean default false")
    private Boolean liked;

	public VideoInteractionKey getId() {
		return id;
	}

	public void setId(VideoInteractionKey id) {
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

	public Boolean isViewed() {
		return viewed;
	}

	public void setView(Boolean view) {
		this.viewed = view;
	}

	public Boolean isLiked() {
		return liked;
	}

	public void setLike(Boolean like) {
		this.liked = like;
	}

	@Override
	public String toString() {
		return "VideoInteraction [id=" + id + ", video=" + video + ", user=" + user + ", viewed=" + viewed + ", liked="
				+ liked + "]";
	}
    
}
