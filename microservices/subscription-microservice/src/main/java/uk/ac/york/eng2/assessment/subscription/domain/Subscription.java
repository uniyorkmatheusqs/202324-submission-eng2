package uk.ac.york.eng2.assessment.subscription.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.micronaut.serde.annotation.Serdeable;

@Entity
@Serdeable
public class Subscription {
    @Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Long userId;

    @Column(nullable = false)
    private String hashtag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    @Override
    public String toString() {
        return "Subscription [id=" + id + ", userId=" + userId + ", hashtag=" + hashtag + "]";
    }
    
}
