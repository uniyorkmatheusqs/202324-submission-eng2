package uk.ac.york.eng2.assessment.video.controllers;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import main.java.uk.ac.york.eng2.assessment.video.controllers.IHealthController;

@Controller(IHealthController.BASE_URI)
public class HealthController implements IHealthController {
    @Override
    public HttpResponse<String> ping() {
        return HttpResponse.ok("pong");
    }   
}
