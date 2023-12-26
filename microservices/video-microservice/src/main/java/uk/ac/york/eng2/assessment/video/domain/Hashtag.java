package uk.ac.york.eng2.assessment.video.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micronaut.serde.annotation.Serdeable;

@Entity
@Serdeable
public class Hashtag {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String value;
	
	@ManyToMany(mappedBy = "hashtags")
	@JsonIgnore
	Set<Video> videos;

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

	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "Hashtag [id=" + id + ", value=" + value + "]";
	}
	
}
