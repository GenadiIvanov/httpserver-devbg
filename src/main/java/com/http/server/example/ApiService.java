package com.http.server.example;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.IOException;

class ApiService {

    String getLectures() {
        return Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("id", "1")
                        .add("lectureTopic", "HTTPClient API in Java 11 - HTTP/2 and Reactive Stream"))
                .add(Json.createObjectBuilder()
                        .add("id", "2")
                        .add("lectureTopic", "Building an Application with Spring Boot"))
                .add(Json.createObjectBuilder()
                        .add("id", "3")
                        .add("lectureTopic", "Java threads and the power of sleep() method"))
                .build().toString();
    }

    String getLectureContent(int id, int seconds) throws InterruptedException, NegativeDelayTimeException {
        if (seconds < 0) {
            throw new NegativeDelayTimeException(String.format("Negative delay in seconds provided: %d", seconds));
        }
        Thread.sleep(seconds * 1000);
        return findLectureContent(id).toString();
    }

    byte[] getFile() throws IOException {
        return getClass().getResourceAsStream("/file.txt").readAllBytes();
    }

    private JsonObject findLectureContent(int id) {
        JsonObjectBuilder lectureContent = Json.createObjectBuilder();
        switch (id) {
            case 1:
                lectureContent.add("id", "1")
                        .add("lecturerName", "Genadi Ivanov")
                        .add("lectureTopic", "HTTPClient API in Java 11 - HTTP/2 and Reactive Stream")
                        .add("content", "Blocking vs Non-Blocking I/O operations. " +
                                "How HTTPClient API in Java 11 leverages Reactive Streams. " +
                                "HTTP/2 - Multiplexing and Server Pushes.");
                break;
            case 2:
                lectureContent.add("id", "2")
                        .add("lecturerName", "Ivan Spasimirov")
                        .add("lectureTopic", "Building an Application with Spring Boot")
                        .add("content", "Learn what you can do with Spring Boot. " +
                                "Spring Boot offers a fast way to build applications.");
                break;
            case 3:
                lectureContent.add("id", "3")
                        .add("lecturerName", "Radoslav Radulov")
                        .add("lectureTopic", "Java threads and the power of sleep() method")
                        .add("content", "sleep() causes the thread to definitely stop executing " +
                                "for a given amount of time; if no other thread or process needs to be run, the CPU will be idle");
                break;
            default:
        }
        return lectureContent.build();
    }

    /**
     * Custom Checked Exception
     */
    static class NegativeDelayTimeException extends Exception {
        NegativeDelayTimeException(String message) {
            super(message);
        }
    }
}
