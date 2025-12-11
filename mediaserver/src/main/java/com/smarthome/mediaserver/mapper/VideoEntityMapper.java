package com.smarthome.mediaserver.mapper;

import com.smarthome.mediaserver.entity.VideoEntity;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
import org.xproce.lab.Video;

@Component
public class VideoEntityMapper {

    public VideoEntity fromUploadRequest(String id, String title, String description,
                                         String url, int durationSeconds, Creator creator) {

        VideoEntity entity = new VideoEntity();
        entity.setId(id);
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setUrl(url);
        entity.setDurationSeconds(durationSeconds);

        if (creator != null) {
            entity.setCreatorId(creator.getId());
            entity.setCreatorName(creator.getName());
            entity.setCreatorEmail(creator.getEmail());
        }

        return entity;
    }

    public Video toProto(VideoEntity entity) {
        Creator creator = Creator.newBuilder()
                .setId(entity.getCreatorId() == null ? "" : entity.getCreatorId())
                .setName(entity.getCreatorName() == null ? "" : entity.getCreatorName())
                .setEmail(entity.getCreatorEmail() == null ? "" : entity.getCreatorEmail())
                .build();

        return Video.newBuilder()
                .setId(entity.getId())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setUrl(entity.getUrl())
                .setDurationSeconds(entity.getDurationSeconds())
                .setCreator(creator)
                .build();
    }
}
