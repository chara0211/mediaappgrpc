package com.smarthome.mediaclient.mapper;

import com.smarthome.mediaclient.dto.CreatorDto;
import com.smarthome.mediaclient.dto.VideoDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
import org.xproce.lab.VideoStream;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreatorMapper {

    private final ModelMapper modelMapper;
    private final VideoMapper videoMapper;

    public CreatorMapper(ModelMapper modelMapper, VideoMapper videoMapper) {
        this.modelMapper = modelMapper;
        this.videoMapper = videoMapper;
    }

    public CreatorDto fromCreatorProto(Creator creator) {
        return modelMapper.map(creator, CreatorDto.class);
    }

    public List<VideoDto> fromVideoStream(VideoStream stream) {
        return stream.getVideosList()
                .stream()
                .map(videoMapper::fromVideoProtoToVideoDto)
                .collect(Collectors.toList());
    }
}
