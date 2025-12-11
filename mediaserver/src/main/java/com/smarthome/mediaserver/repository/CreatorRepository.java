package com.smarthome.mediaserver.repository;

import com.smarthome.mediaserver.entity.CreatorEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CreatorRepository {

    private final Map<String, CreatorEntity> store = new ConcurrentHashMap<>();

    public CreatorRepository() {
        // seed de données pour les tests
        CreatorEntity c1 = new CreatorEntity();
        c1.setId("1");
        c1.setName("Wafaa");
        c1.setEmail("Wafaa@example.com");
        save(c1);

        CreatorEntity c2 = new CreatorEntity();
        c2.setId("2");
        c2.setName("Wafaa2");
        c2.setEmail("Wafaa2@example.com");
        save(c2);
    }

    public CreatorEntity save(CreatorEntity creator) {
        store.put(creator.getId(), creator);
        return creator;
    }

    public CreatorEntity findById(String id) {
        return store.get(id);
    }
}
