package uk.ac.york.eng2.assessment.trending.controllers;

import io.micronaut.http.HttpResponse;
import main.java.uk.ac.york.eng2.assessment.trending.controllers.IHealthController;

public class HealthController implements IHealthController {
    @Override
    public HttpResponse<String> ping() {
        return HttpResponse.ok("pong");
    }   
}