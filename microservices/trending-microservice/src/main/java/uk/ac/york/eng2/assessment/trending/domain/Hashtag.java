package uk.ac.york.eng2.assessment.trending.domain;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class Hashtag {
    private Long id;

    private String value;

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

    
}
