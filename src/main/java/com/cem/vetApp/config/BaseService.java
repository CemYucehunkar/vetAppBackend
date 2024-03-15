package com.cem.vetApp.config;

import java.util.List;

public interface BaseService<Entity, EntityRequest, EntityResponse> {
    Entity getById(Long id);
    EntityResponse getResponseById(Long id);
    List<Entity> getAll();
    List<EntityResponse> getAllResponses();
    EntityResponse create(EntityRequest entityRequest);
    EntityResponse update(Long id, EntityRequest entityRequest);
    void deleteById(Long id);
}
