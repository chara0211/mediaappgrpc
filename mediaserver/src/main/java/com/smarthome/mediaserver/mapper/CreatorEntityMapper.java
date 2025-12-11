package com.smarthome.mediaserver.mapper;

import com.smarthome.mediaserver.entity.CreatorEntity;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;

@Component
public class CreatorEntityMapper {

    public Creator toProto(CreatorEntity entity) {
        return Creator.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .build();
    }

    public CreatorEntity toEntity(Creator proto) {
        CreatorEntity entity = new CreatorEntity();
        entity.setId(proto.getId());
        entity.setName(proto.getName());
        entity.setEmail(proto.getEmail());
        return entity;
    }
}
