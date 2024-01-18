package uk.ac.york.eng2.assessment.recommendation.controllers;

import java.util.List;

import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import main.java.uk.ac.york.eng2.assessment.recommendation.controllers.IHashtagsController;
import main.java.uk.ac.york.eng2.assessment.recommendation.dtos.ListHashtagsDTO;
import uk.ac.york.eng2.assessment.recommendation.repositories.SubscriptionRepository;

public class HashtagsController implements IHashtagsController {
    @Inject
    SubscriptionRepository repo;

    @Override
    public HttpResponse<List<String>> list(ListHashtagsDTO listHashtagsDTO) {
        return HttpResponse.ok(repo.listHashtags(listHashtagsDTO.getHashtag()));
    }
    
}
