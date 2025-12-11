package com.smarthome.mediaclient.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreatorDto {
    private String id;
    private String name;
    private String email;

    // pour l’endpoint /creators/{id}/videos
    private List<VideoDto> videos;
}
