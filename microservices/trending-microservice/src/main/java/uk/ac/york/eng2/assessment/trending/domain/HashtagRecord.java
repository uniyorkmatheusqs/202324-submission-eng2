package uk.ac.york.eng2.assessment.trending.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Entity
public class HashtagRecord {
    @Id
	@GeneratedValue 
	private Long id;

    @Column(nullable = false)
	private String hashtag;

    @Column(nullable = false)
	private Long startMillis;
    
    @Column(nullable = false)
	private Long endMillis;

    @Column(nullable = false)
	private Long likes;
    
    @Column
    @DateCreated
    private Date createdAt;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public Long getStartMillis() {
        return startMillis;
    }

    public void setStartMillis(Long startMillis) {
        this.startMillis = startMillis;
    }

    public Long getEndMillis() {
        return endMillis;
    }

    public void setEndMillis(Long endMillis) {
        this.endMillis = endMillis;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }    

    @Override
    public String toString() {
        return "HashtagRecord [id=" + id + ", hashtag=" + hashtag + ", startMillis=" + startMillis + ", endMillis="
                + endMillis + ", createdAt=" + createdAt + "]";
    }
    
}
