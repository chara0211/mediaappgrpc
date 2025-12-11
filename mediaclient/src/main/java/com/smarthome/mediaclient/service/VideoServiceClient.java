package com.smarthome.mediaclient.service;

import com.smarthome.mediaclient.dto.VideoDto;
import com.smarthome.mediaclient.mapper.VideoMapper;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.lab.*;

@Service
public class VideoServiceClient {

    // Stub gRPC (client)
    @GrpcClient("mediaserver")
    private VideoServiceGrpc.VideoServiceBlockingStub stub;

    @Autowired
    private VideoMapper mapper;

    public VideoDto uploadVideo(UploadVideoRequest request) {
        Video video = stub.uploadVideo(request);
        return mapper.fromVideoProtoToVideoDto(video);
    }

    public VideoDto getVideoById(String id) {
        VideoIdRequest request = VideoIdRequest.newBuilder()
                .setId(id)
                .build();

        Video video = stub.getVideo(request);
        return mapper.fromVideoProtoToVideoDto(video);
    }
}
