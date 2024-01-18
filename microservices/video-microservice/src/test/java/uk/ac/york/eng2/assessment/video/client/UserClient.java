package test.java.uk.ac.york.eng2.assessment.video.client;

import java.util.List;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import main.java.uk.ac.york.eng2.assessment.video.cli.dtos.*;

@Client("${video.url:`http://localhost:8080/users`}")
public interface UserClient {
    
    @Post("/")
    public HttpResponse<String> create(@Body CreateUserDTO createUserDTO);
    
    @Get("/{id}")
    public HttpResponse<String> get(Long id);
    
}

