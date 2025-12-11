package com.smarthome.mediaclient.web;

import com.smarthome.mediaclient.dto.VideoDto;
import com.smarthome.mediaclient.service.VideoServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xproce.lab.Creator;
import org.xproce.lab.UploadVideoRequest;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private VideoServiceClient videoServiceClient;

    // POST /api/videos/add
    @PostMapping("/add")
    public VideoDto uploadVideo() {

        // Dans le TP, ils construisent la requête côté serveur (hardcodée)
        Creator creator = Creator.newBuilder()
                .setId("2")
                .setName("Xproce")
                .setEmail("elhadchiwafaa@gmail.com")
                .build();

        UploadVideoRequest request = UploadVideoRequest.newBuilder()
                .setTitle("grpc 101")
                .setDescription("The gRPC 101 is an introductory course to master gRPC")
                .setUrl("https://github.com/wafaa/gRPC101")
                .setDurationSeconds(380)
                .setCreator(creator)
                .build();

        return videoServiceClient.uploadVideo(request);
    }

    // Exemple bonus: GET /api/videos/{id}
    @GetMapping("/{id}")
    public VideoDto getVideo(@PathVariable String id) {
        return videoServiceClient.getVideoById(id);
    }
}
