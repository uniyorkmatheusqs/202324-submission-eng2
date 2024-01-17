package uk.ac.york.eng2.assessment.trending.controllers;

import java.util.List;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;
import main.java.uk.ac.york.eng2.assessment.trending.controllers.IHashtagController;
import uk.ac.york.eng2.assessment.trending.repositories.HashtagsRepository;

@Controller(IHashtagController.BASE_URI)
public class HashtagController implements IHashtagController {

    @Inject
    private HashtagsRepository repository;

    @Override
    public HttpResponse<List<String>> list() {
        long lastHourTimestamp = System.currentTimeMillis() - (1 * 60 * 60 * 1000);
        return HttpResponse.ok(repository.listTopHashtags(lastHourTimestamp)) ;
    }
}
