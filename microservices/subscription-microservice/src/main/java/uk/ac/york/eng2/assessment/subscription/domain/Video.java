package uk.ac.york.eng2.assessment.subscription.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.serde.annotation.Serdeable;

@Entity
@Serdeable
public class Video {
    @Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Long videoId;

    @Column
    @DateCreated
    private Date createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Hashtag> hashtags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Video [id=" + id + ", videoId=" + videoId + ", createdAt=" + createdAt + ", hashtags=" + hashtags + "]";
    }

}
