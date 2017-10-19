package com.byteknowledge.bilt.data.service;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dto.UUIDDto;
import com.byteknowledge.bilt.model.AbstractUUIDEntity;
import com.byteknowledge.bilt.service.Service;

public abstract class AbstractServiceRedis<T extends UUIDDto, D extends Dao<E>, E extends AbstractUUIDEntity>
        implements Service<T,D,E> {

}
