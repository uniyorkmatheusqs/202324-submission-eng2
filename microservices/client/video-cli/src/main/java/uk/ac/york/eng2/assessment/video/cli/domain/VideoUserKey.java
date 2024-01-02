package uk.ac.york.eng2.assessment.video.cli.domain;

import java.io.Serializable;
import java.util.Objects;

public class VideoUserKey implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long videoId;

    private Long userId;

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		VideoUserKey other = (VideoUserKey) obj;
		return Objects.equals(userId, other.userId) && Objects.equals(videoId, other.videoId);
	}

	@Override
	public String toString() {
		return "VideoUserKey [videoId=" + videoId + ", userId=" + userId + "]";
	}
	
}
