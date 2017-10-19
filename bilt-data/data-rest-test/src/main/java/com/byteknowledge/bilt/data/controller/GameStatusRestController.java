package com.byteknowledge.bilt.data.controller;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.bilt.dao.GameStatusDao;
import com.byteknowledge.bilt.model.GameStatus;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class GameStatusRestController {

	@Autowired
	private GameStatusDao gameStatusDao;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Collection<GameStatus> getList() {
        return getAllEntities();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody GameStatus get(@PathVariable("id") final UUID id) {
        return getEntity(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public void create(@RequestBody final GameStatus gameStatus) {
        createEntity(gameStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json")
    public void save(@PathVariable("id") final UUID id, final @RequestBody GameStatus gameStatus) {
        final GameStatus persistedGameStatus = gameStatusDao.get(id);
        updateEntity(persistedGameStatus, gameStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") final UUID id) {
        final GameStatus persistedGameStatus = gameStatusDao.get(id);
        if (persistedGameStatus != null) {
            gameStatusDao.remove(persistedGameStatus);
        }
    }

    protected void createEntity(final GameStatus gameStatus) {
        gameStatusDao.save(gameStatus);
    }

    protected void updateEntity(final GameStatus persistedGameStatus, final GameStatus gameStatus) {
        merge(persistedGameStatus, gameStatus);
        gameStatusDao.save(persistedGameStatus);
    }

    protected GameStatus getEntity(final UUID id) {
        return gameStatusDao.get(id);
    }

    protected Collection<GameStatus> getAllEntities() {
        return gameStatusDao.list();
    }

	protected void merge(final GameStatus persistedGameStatus, final GameStatus gameStatus) {
		persistedGameStatus.setState(gameStatus.getState());
		persistedGameStatus.setPeriod(gameStatus.getPeriod());
		persistedGameStatus.setClock(gameStatus.getClock());
		persistedGameStatus.setLastModifiedTimestamp(gameStatus.getLastModifiedTimestamp());
	}    
}
