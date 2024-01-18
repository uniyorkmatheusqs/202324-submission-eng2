package test.java.uk.ac.york.eng2.assessment.video.client;

import java.util.List;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import main.java.uk.ac.york.eng2.assessment.video.cli.dtos.*;
import uk.ac.york.eng2.assessment.video.cli.domain.*;

@Client("${video.url:`http://localhost:8080/videos`}")
public interface VideoClient {
    
    @Get("/")
    public List<Video> list();
    
    @Post("/")
    public HttpResponse<String> create(@Body CreateVideoDTO createVideoDTO);
    
    @Get("/{id}")
    public Video listOne(Long id);
    
    @Put("/{id}/watch")
    public HttpResponse<String> watch(Long id, @Body WatchVideoDTO watchVideoDTO);
    
    @Put("/{id}/like")
    public HttpResponse<String> like(Long id, @Body LikeVideoDTO likeVideoDTO);
    
    @Put("/{id}/dislike")
    public HttpResponse<String> dislike(Long id, @Body DislikeVideoDTO dislikeVideoDTO);
    
}

