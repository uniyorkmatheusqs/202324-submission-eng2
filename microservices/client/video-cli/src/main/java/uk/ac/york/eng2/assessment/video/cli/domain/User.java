package uk.ac.york.eng2.assessment.video.cli.domain;

import java.util.Set;

public class User {
	private Long id;
	
	private String name;
	
    private Set<VideoUser> videosDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<VideoUser> getVideosDetails() {
		return videosDetails;
	}

	public void setVideosDetails(Set<VideoUser> videosDetails) {
		this.videosDetails = videosDetails;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", videosDetails=" + videosDetails + "]";
	}
}
