package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EntityService {
    private static final String ID = "id";

    private final EntityRepository repository;

    public EntityService(EntityRepository repository) {
        this.repository = repository;
    }

    public Map save(String type, Map<String, Object> entityFields){
        Entity entity = new Entity();

        var id = entityFields.get(ID);
        if (id != null) {
            entity.setId(id.toString());
            entityFields.remove(ID);
        }

        entity.setType(type);
        entity.setFields(entityFields);
        var computedId = repository.save(entity).getId();

        entityFields.computeIfAbsent(ID, key -> computedId);
        return entityFields;
    }

    public Map find(String id, String type){
        Entity entity = repository.findByIdAndType(id, type);
        Map<String, Object> entityFields = entity.getFields();
        entityFields.put(ID, entity.getId());
        return entityFields;
    }

    public List findAll(String type, Pageable paging){
        Page<Entity> entities = repository.findByType(type, paging);
        List<Map<String, Object>> entitiesFields = new ArrayList<>(entities.getSize());
        for (Entity entity : entities) {
            Map<String, Object> entityFields = entity.getFields();
            entityFields.put(ID, entity.getId());
            entitiesFields.add(entityFields);
        }
        return entitiesFields;
    }

    public Map update(String id, String type, Map<String, Object> entity){
        entity.put(ID, id);
        return save(type, entity);
    }

    public void delete(String id, String type){
        repository.removeByIdAndType(id, type);
    }
}
