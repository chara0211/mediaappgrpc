package com.smarthome.mediaclient.mapper;

import com.smarthome.mediaclient.dto.CreatorDto;
import com.smarthome.mediaclient.dto.VideoDto;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
import org.xproce.lab.VideoStream;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreatorMapper {

    private final VideoMapper videoMapper;

    public CreatorMapper(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    public CreatorDto fromCreatorProto(Creator creator) {
        CreatorDto dto = new CreatorDto();
        dto.setId(creator.getId());
        dto.setName(creator.getName());
        dto.setEmail(creator.getEmail());
        return dto;
    }

    public List<VideoDto> fromVideoStream(VideoStream stream) {
        return stream.getVideosList()
                .stream()
                .map(videoMapper::fromVideoProtoToVideoDto)
                .collect(Collectors.toList());
    }
}
