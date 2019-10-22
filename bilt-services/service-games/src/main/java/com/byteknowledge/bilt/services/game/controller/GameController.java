package com.byteknowledge.bilt.services.game.controller;

import java.util.Collection;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.bilt.dto.GameDto;
import com.byteknowledge.bilt.services.game.service.GameService;

@RestController
@RequestMapping("/api/games")
public class GameController extends AbstractGameController {

    private final static Logger LOG = LogManager.getLogger(GameController.class);

    @Autowired
    private GameService gameService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody GameDto get(@PathVariable("id") final UUID id) {
        return gameService.getGame(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    final public @ResponseBody Collection<GameDto> getList() {
        return gameService.getGamesList();
    }

}
