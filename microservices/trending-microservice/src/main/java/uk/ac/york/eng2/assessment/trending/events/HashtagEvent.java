package uk.ac.york.eng2.assessment.trending.events;

public class HashtagEvent {
    private Long hashtagId;
    
    private Long count;

    
    public HashtagEvent(Long hashtagId, Long count) {
        this.hashtagId = hashtagId;
        this.count = count;
    }

    public Long getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(Long hashtagId) {
        this.hashtagId = hashtagId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
    
}
