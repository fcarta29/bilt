package com.byteknowledge.bilt.service;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dto.UUIDDto;
import com.byteknowledge.bilt.model.AbstractUUIDEntity;

public interface Service<T extends UUIDDto, D extends Dao<E>, E extends AbstractUUIDEntity> {

    public static final String USER1_ID = "0ac0d6d4-f761-4481-b2a7-03770da268f9";
    public static final String USER2_ID = "7dc8368a-8d7d-426a-8009-9e040211c109";

    public T entityToDto(final E entity);

    public E dtoToEntity(final T dto);
}
