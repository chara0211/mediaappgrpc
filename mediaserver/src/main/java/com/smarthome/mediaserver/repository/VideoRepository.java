package com.smarthome.mediaserver.repository;

import com.smarthome.mediaserver.entity.VideoEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class VideoRepository {

    private final Map<String, VideoEntity> store = new ConcurrentHashMap<>();

    public VideoEntity save(VideoEntity video) {
        store.put(video.getId(), video);
        return video;
    }

    public VideoEntity findById(String id) {
        return store.get(id);
    }

    public Collection<VideoEntity> findAll() {
        return store.values();
    }

    // toutes les vidéos d’un créateur
    public List<VideoEntity> findByCreatorId(String creatorId) {
        return store.values().stream()
                .filter(v -> creatorId.equals(v.getCreatorId()))
                .collect(Collectors.toList());
    }
}
