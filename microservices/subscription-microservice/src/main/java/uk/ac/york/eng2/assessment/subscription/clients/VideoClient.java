package uk.ac.york.eng2.assessment.subscription.clients;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.subscription.domain.Video;


@Client("${videos.url:`http://localhost:8080/videos`}")
public interface VideoClient {

    @Get("/{id}")
    public Video listOne(Long id);

}
