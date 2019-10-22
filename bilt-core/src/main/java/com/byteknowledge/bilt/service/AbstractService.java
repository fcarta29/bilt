package com.byteknowledge.bilt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dto.AbstractDto;
import com.byteknowledge.bilt.dto.UUIDDto;
import com.byteknowledge.bilt.model.AbstractUUIDEntity;

public abstract class AbstractService<T extends AbstractDto, E extends AbstractUUIDEntity, D extends Dao<E>>
		implements Service<T,E,D> {

    protected static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    
    protected abstract Dao<E> getDao();

    //protected abstract void merge(final E persitedEntity, final E newEntity);
    
    protected Collection<T> getAll() {
    	return entitiesToDtos(getDao().list());
    }

    protected T get(final UUID id) {
        return entityToDto(getDao().get(id));
    }

    protected void create(final E entity) {
    	getDao().save(entity);
    }

    protected void save(final T dto) {
    	getDao().save(dtoToEntity(dto));
    }

    protected void delete(final UUID id) {
        final E persistedEntity = getDao().get(id);
        if (persistedEntity != null) {
            getDao().remove(persistedEntity);
        }
    }     

    public Collection<T> entitiesToDtos(final Collection<E> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.<T> emptyList();
        }

        final List<T> dtoList = new ArrayList<T>();
        for (final E entity : entities) {
            dtoList.add(entityToDto(entity));
        }
        return dtoList;
    }

    public Collection<E> dtosToEntities(final Collection<T> dtos) {
        if (CollectionUtils.isEmpty(dtos)) {
            return Collections.<E> emptyList();
        }

        final List<E> entityList = new ArrayList<E>();
        for (final T dto : dtos) {
        	entityList.add(dtoToEntity(dto));
        }
        return entityList;
    }
    
	protected static final Collection<UUID> dtosToUUIDs(final Collection<? extends UUIDDto> dtos) {
		final Collection<UUID> uuids = new HashSet<UUID>();
		for (final UUIDDto dto : dtos) {
			uuids.add(UUID.fromString(dto.getId()));
		}
		return uuids;
	}
}
