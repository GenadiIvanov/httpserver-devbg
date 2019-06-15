package com.http.server.example;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import java.io.IOException;

@RestController
@Component
public class ApiController {

    @GetMapping(value = "/api/lectures", produces = "application/json")
    public String getLectures() {
        return new ApiService().getLectures();
    }

    @GetMapping(value = "/api/lectures/{id}/content", produces = "application/json")
    public ResponseEntity<String> getLectureContent(
            @PathVariable("id") int id,
            @RequestParam("delay") int seconds) throws InterruptedException {
        try {
            return ResponseEntity.ok().body(new ApiService().getLectureContent(id, seconds));
        } catch (ApiService.NegativeDelayTimeException ex) {
            return ResponseEntity.badRequest()
                    .body(Json.createObjectBuilder().add("error", ex.getMessage()).build().toString());
        }
    }

    @GetMapping(value = "/api/file", produces = "application/octet-stream")
    public byte[] getFile() throws IOException {
        return new ApiService().getFile();
    }
}
