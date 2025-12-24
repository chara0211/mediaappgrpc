package com.smarthome.mediaclient.mapper;

import com.smarthome.mediaclient.dto.VideoDto;
import com.smarthome.mediaclient.dto.VideoStreamDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xproce.lab.VideoStream;

import java.util.List;

@Component
public class VideoMapper {

    private final ModelMapper modelMapper;

    public VideoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public VideoDto fromVideoProtoToVideoDto(org.xproce.lab.Video video) {
        VideoDto dto = modelMapper.map(video, VideoDto.class);

        if (video.hasCreator()) {
            dto.setCreatorId(video.getCreator().getId());
            dto.setCreatorName(video.getCreator().getName());
            dto.setCreatorEmail(video.getCreator().getEmail());
        }

        return dto;
    }

    public VideoStreamDto fromVideoStreamProtoToVideoStreamDto(VideoStream stream) {
        List<VideoDto> videos = stream.getVideosList()
                .stream()
                .map(this::fromVideoProtoToVideoDto)
                .toList();

        return new VideoStreamDto(videos);
    }
}
