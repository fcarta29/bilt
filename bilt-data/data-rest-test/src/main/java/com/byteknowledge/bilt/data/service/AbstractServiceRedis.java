package com.byteknowledge.bilt.data.service;

import com.byteknowledge.bilt.dao.Dao;
import com.byteknowledge.bilt.dto.AbstractDto;
import com.byteknowledge.bilt.model.AbstractUUIDEntity;
import com.byteknowledge.bilt.service.Service;

public abstract class AbstractServiceRedis<T extends AbstractDto, E extends AbstractUUIDEntity, D extends Dao<E>>
        implements Service<T,E,D> {

}
