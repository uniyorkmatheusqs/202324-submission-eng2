package uk.ac.york.eng2.assessment.trending.clients;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.assessment.trending.domain.Video;


@Client("${videos.url:`http://localhost:8080/videos`}")
public interface VideoClient {

    @Get("/{id}")
    public Video listOne(Long id);

}
