package com.byteknowledge.bilt.services.game.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.byteknowledge.bilt.dao.GameDao;
import com.byteknowledge.bilt.dao.GameTeamDao;
import com.byteknowledge.bilt.dao.GameWeekDao;
import com.byteknowledge.bilt.dao.TeamDao;
import com.byteknowledge.bilt.dto.Game2Dto;
import com.byteknowledge.bilt.dto.GameTeamDto;
import com.byteknowledge.bilt.model.Game;
import com.byteknowledge.bilt.model.GameTeam;
import com.byteknowledge.bilt.model.GameWeek;
import com.byteknowledge.bilt.model.Team;

@RestController
@RequestMapping("/api/week/{id}")
public class GameWeekController extends AbstractGameController {
    
    private static final Logger LOG = Logger.getLogger(GameWeekController.class);
    
    @Autowired
    private GameDao gameDao;

    @Autowired
    private GameWeekDao gameWeekDao;
    
    @Autowired
    private TeamDao teamDao;    
    
    @Autowired
    private GameTeamDao gameTeamDao;  

    @GetMapping(value = "/games", produces = "application/json")
    public @ResponseBody Collection<Game2Dto> games(@PathVariable("id") final UUID weekId) {
        Collection<Game2Dto> gameDtos = new ArrayList<Game2Dto>();
        
        final GameWeek gameWeek = gameWeekDao.get(weekId);
        if (gameWeek == null) {
            // throw new IllegalArgumentException("Week " + weekId + " cannot be found!");
            return CollectionUtils.emptyCollection();
        }
        
        final Collection<Game> games = gameDao.findByDateRange(gameWeek.getStartTimestamp(), gameWeek.getEndTimestamp());
        for (final Game game : games) {
            final Game2Dto gameDto = new Game2Dto();
            gameDto.setId(game.getId());
            gameDto.setGameTime(new Date(game.getStartTimestamp()));
            
            final Team homeTeam = teamDao.get(game.getHomeTeamId());
            final GameTeam homeGameTeam = gameTeamDao.findByGameAndTeam(game.getId(), homeTeam.getId());
            final GameTeamDto homeTeamDto = new GameTeamDto();
            homeTeamDto.setId(homeTeam.getId());
            homeTeamDto.setName(homeTeam.getName());
            homeTeamDto.setAbbreviation(homeTeam.getAbbreviation());
            homeTeamDto.setSpread(homeGameTeam.getSpread());
            homeTeamDto.setScore(homeGameTeam.getScore());
            homeTeamDto.setLastUpdatedTime(homeGameTeam.getLastUpdatedTime());
            gameDto.setHomeTeam(homeTeamDto);
            
            final Team awayTeam = teamDao.get(game.getAwayTeamId());
            final GameTeam awayGameTeam = gameTeamDao.findByGameAndTeam(game.getId(), awayTeam.getId());
            final GameTeamDto awayTeamDto = new GameTeamDto();
            awayTeamDto.setId(awayTeam.getId());
            awayTeamDto.setName(awayTeam.getName());
            awayTeamDto.setAbbreviation(awayTeam.getAbbreviation());
            awayTeamDto.setSpread(awayGameTeam.getSpread());
            awayTeamDto.setScore(awayGameTeam.getScore());
            awayTeamDto.setLastUpdatedTime(awayGameTeam.getLastUpdatedTime());
            gameDto.setAwayTeam(awayTeamDto);
            
            gameDtos.add(gameDto);
        }
        
        
        return gameDtos;
    }
    
}
