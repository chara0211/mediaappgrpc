package com.smarthome.mediaclient.web;

import com.smarthome.mediaclient.dto.CreatorDto;
import com.smarthome.mediaclient.dto.VideoDto;
import com.smarthome.mediaclient.service.CreatorServiceClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creators")
public class CreatorController {

    private final CreatorServiceClient creatorServiceClient;

    public CreatorController(CreatorServiceClient creatorServiceClient) {
        this.creatorServiceClient = creatorServiceClient;
    }

    // GET /api/creators/{id}
    @GetMapping("/{id}")
    public CreatorDto getCreator(@PathVariable String id) {
        return creatorServiceClient.getCreatorById(id);
    }

    // GET /api/creators/{id}/videos
    @GetMapping("/{id}/videos")
    public List<VideoDto> getCreatorVideos(@PathVariable String id) {
        return creatorServiceClient.getCreatorVideos(id);
    }
}
