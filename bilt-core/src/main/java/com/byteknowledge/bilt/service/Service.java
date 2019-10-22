package com.byteknowledge.bilt.service;

import java.util.Collection;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dto.AbstractDto;
import com.byteknowledge.bilt.model.AbstractUUIDEntity;

public interface Service<T extends AbstractDto, E extends AbstractUUIDEntity, D extends Dao<E>> {

    public T entityToDto(final E entity);

    public E dtoToEntity(final T dto);
    
    public Collection<T> entitiesToDtos(final Collection<E> entities);
    
    public Collection<E> dtosToEntities(final Collection<T> dtos);
}
