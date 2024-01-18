package uk.ac.york.eng2.assessment.recommendation.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.serde.annotation.Serdeable;

@Entity
@Serdeable
public class Subscription {
    @Id
	@GeneratedValue
	private Long id;

    @MapsId("id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Hashtag hashtag;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Long userId;

    @Column
    @DateCreated
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }

    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Subscription [id=" + id + ", hashtag=" + hashtag + ", active=" + active + ", userId=" + userId
                + ", createdAt=" + createdAt + "]";
    }
    
}
