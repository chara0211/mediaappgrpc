package com.smarthome.mediaclient.service;

import com.smarthome.mediaclient.dto.CreatorDto;
import com.smarthome.mediaclient.dto.VideoDto;
import com.smarthome.mediaclient.mapper.CreatorMapper;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.xproce.lab.*;

import java.util.List;

@Service
public class CreatorServiceClient {

    @GrpcClient("mediaserver")
    private CreatorServiceGrpc.CreatorServiceBlockingStub stub;

    private final CreatorMapper creatorMapper;

    public CreatorServiceClient(CreatorMapper creatorMapper) {
        this.creatorMapper = creatorMapper;
    }

    public CreatorDto getCreatorById(String id) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder()
                .setId(id)
                .build();

        Creator creator = stub.getCreator(request);
        return creatorMapper.fromCreatorProto(creator);
    }

    public List<VideoDto> getCreatorVideos(String creatorId) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder()
                .setId(creatorId)
                .build();

        VideoStream stream = stub.getCreatorVideos(request);
        return creatorMapper.fromVideoStream(stream);
    }
}
