package uk.ac.york.eng2.assessment.video.cli.domain;

import java.util.List;

public class Hashtag {
	private Long id;
	
	private String value;
	
	List<Video> videos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "Hashtag [id=" + id + ", value=" + value + ", videos=" + videos + "]";
	}
}
