package com.smarthome.mediaserver.grpc;

import com.smarthome.mediaserver.entity.CreatorEntity;
import com.smarthome.mediaserver.entity.VideoEntity;
import com.smarthome.mediaserver.mapper.CreatorEntityMapper;
import com.smarthome.mediaserver.mapper.VideoEntityMapper;
import com.smarthome.mediaserver.repository.CreatorRepository;
import com.smarthome.mediaserver.repository.VideoRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.xproce.lab.*;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CreatorService extends CreatorServiceGrpc.CreatorServiceImplBase {

    private final CreatorRepository creatorRepository;
    private final VideoRepository videoRepository;
    private final CreatorEntityMapper creatorMapper;
    private final VideoEntityMapper videoMapper;

    public CreatorService(CreatorRepository creatorRepository,
                          VideoRepository videoRepository,
                          CreatorEntityMapper creatorMapper,
                          VideoEntityMapper videoMapper) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.creatorMapper = creatorMapper;
        this.videoMapper = videoMapper;
    }

    @Override
    public void getCreator(CreatorIdRequest request,
                           StreamObserver<Creator> responseObserver) {

        CreatorEntity entity = creatorRepository.findById(request.getId());

        if (entity == null) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("Creator not found: " + request.getId())
                            .asRuntimeException()
            );
            return;
        }

        Creator creator = creatorMapper.toProto(entity);
        responseObserver.onNext(creator);
        responseObserver.onCompleted();
    }

    @Override
    public void getCreatorVideos(CreatorIdRequest request,
                                 StreamObserver<VideoStream> responseObserver) {

        List<VideoEntity> entities = videoRepository.findByCreatorId(request.getId());

        List<Video> videos = entities.stream()
                .map(videoMapper::toProto)
                .collect(Collectors.toList());

        VideoStream stream = VideoStream.newBuilder()
                .addAllVideos(videos)
                .build();

        responseObserver.onNext(stream);
        responseObserver.onCompleted();
    }
}
