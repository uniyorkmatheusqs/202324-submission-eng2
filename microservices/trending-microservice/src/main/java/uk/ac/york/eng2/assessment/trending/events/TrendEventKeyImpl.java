package uk.ac.york.eng2.assessment.trending.events;

import main.java.uk.ac.york.eng2.assessment.trending.events.TrendEventKey;

public class TrendEventKeyImpl extends TrendEventKey{
    TrendEventKeyImpl(String hashtag, Long startMillis, Long endMillis) {
        this.setHashtag(hashtag);
        this.setStartMillis(startMillis);
        this.setEndMillis(endMillis);
    }
}
