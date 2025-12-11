package com.smarthome.mediaserver.grpc;

import com.smarthome.mediaserver.entity.VideoEntity;
import com.smarthome.mediaserver.mapper.VideoEntityMapper;
import com.smarthome.mediaserver.repository.VideoRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.xproce.lab.*;

import java.util.UUID;

@GrpcService
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase {

    private final VideoRepository videoRepository;
    private final VideoEntityMapper videoMapper;

    public VideoService(VideoRepository videoRepository, VideoEntityMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {

        String id = UUID.randomUUID().toString();

        VideoEntity entity = videoMapper.fromUploadRequest(
                id,
                request.getTitle(),
                request.getDescription(),
                request.getUrl(),
                request.getDurationSeconds(),
                request.getCreator()
        );

        VideoEntity saved = videoRepository.save(entity);

        Video response = videoMapper.toProto(saved);

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        VideoEntity entity = videoRepository.findById(request.getId());

        if (entity == null) {
            // même comportement que avant : NOT_FOUND
            Video notFound = Video.newBuilder()
                    .setId(request.getId())
                    .setTitle("NOT_FOUND")
                    .build();
            responseObserver.onNext(notFound);
        } else {
            responseObserver.onNext(videoMapper.toProto(entity));
        }
        responseObserver.onCompleted();
    }
}
