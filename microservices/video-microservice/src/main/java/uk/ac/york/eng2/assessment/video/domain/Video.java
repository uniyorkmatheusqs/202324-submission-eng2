package uk.ac.york.eng2.assessment.video.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micronaut.serde.annotation.Serdeable;

@Entity
@Serdeable
public class Video {
	@Id
	@GeneratedValue 
	private Long id;
	 
	@Column(nullable = false)
	private String title;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name="user_id", nullable=false)
    private User user;
	
	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonIgnore
    private Set<VideoInteraction> usersDetails;
	
	@ManyToMany(fetch = FetchType.EAGER)
    private List<Hashtag> hashtags;

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

	public List<Hashtag> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<Hashtag> hashtags) {
		this.hashtags = hashtags;
	}

	public Set<VideoInteraction> getUsersDetails() {
		return usersDetails;
	}

	public void setUsersDetails(Set<VideoInteraction> usersDetails) {
		this.usersDetails = usersDetails;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", user=" + user + ", hashtags=" + hashtags + "]";
	}
	
}
