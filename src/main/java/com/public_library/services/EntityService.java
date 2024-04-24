package com.public_library.services;

import java.util.List;

public interface EntityService<T, ID> {
    List<T> findAllEntities();

    T findEntityByTitle(String title);

    T createEntity(T entity);

    T updateEntity(T entity);

    void deleteEntity(String title);
}
