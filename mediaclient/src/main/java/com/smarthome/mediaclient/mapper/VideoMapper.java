package com.smarthome.mediaclient.mapper;

import com.smarthome.mediaclient.dto.VideoDto;
import org.springframework.stereotype.Component;
import org.xproce.lab.Video;

@Component
public class VideoMapper {

    public VideoDto fromVideoProtoToVideoDto(Video video) {
        VideoDto dto = new VideoDto();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());
        dto.setDurationSeconds(video.getDurationSeconds());

        if (video.hasCreator()) {
            dto.setCreatorId(video.getCreator().getId());
            dto.setCreatorName(video.getCreator().getName());
            dto.setCreatorEmail(video.getCreator().getEmail());
        }

        return dto;
    }
}
