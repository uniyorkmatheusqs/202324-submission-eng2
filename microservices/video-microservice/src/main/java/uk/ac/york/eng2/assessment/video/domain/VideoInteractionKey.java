package uk.ac.york.eng2.assessment.video.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import io.micronaut.serde.annotation.Serdeable;

@Embeddable
@Serdeable
public class VideoInteractionKey implements Serializable {

	@Column(name = "video_id")
    private Long videoId;

    @Column(name = "user_id")
    private Long userId;

	public VideoInteractionKey() { }

	public VideoInteractionKey(Long videoId, Long userId) {
		this.videoId = videoId;
		this.userId = userId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public Long getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, videoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoInteractionKey other = (VideoInteractionKey) obj;
		return Objects.equals(userId, other.userId) && Objects.equals(videoId, other.videoId);
	}

	@Override
	public String toString() {
		return "VideoInteractionKey [videoId=" + videoId + ", userId=" + userId + "]";
	}
	
}
