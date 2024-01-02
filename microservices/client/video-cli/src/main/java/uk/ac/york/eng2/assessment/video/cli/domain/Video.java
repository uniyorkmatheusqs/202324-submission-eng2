package uk.ac.york.eng2.assessment.video.cli.domain;

import java.util.Set;

public class Video {
	private Long id;
	 
	private String title;
	
    private User user;
	
    private Set<VideoUser> usersDetails;
	
    private Set<Hashtag> hashtags;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(Set<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public Set<VideoUser> getUsersDetails() {
		return usersDetails;
	}

	public void setUsersDetails(Set<VideoUser> usersDetails) {
		this.usersDetails = usersDetails;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", user=" + user + ", usersDetails=" + usersDetails
				+ ", hashtags=" + hashtags + "]";
	}
}
