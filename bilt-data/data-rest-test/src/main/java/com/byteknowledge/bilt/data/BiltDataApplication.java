package com.byteknowledge.bilt.data;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.byteknowledge.bilt.dao.GameDao;
import com.byteknowledge.bilt.dao.GamePickDao;
import com.byteknowledge.bilt.dao.GameStatusDao;
import com.byteknowledge.bilt.dao.GameWeekDao;
import com.byteknowledge.bilt.dao.TeamDao;
import com.byteknowledge.bilt.dao.UserDao;
import com.byteknowledge.bilt.model.Game;
import com.byteknowledge.bilt.model.GamePick;
import com.byteknowledge.bilt.model.GameStatus;
import com.byteknowledge.bilt.model.GameStatus.GamePeriod;
import com.byteknowledge.bilt.model.GameStatus.GameState;
import com.byteknowledge.bilt.model.GameWeek;
import com.byteknowledge.bilt.model.Team;
import com.byteknowledge.bilt.model.User;

@SpringBootApplication
public class BiltDataApplication {

    public static final String USER1_ID = "0ac0d6d4-f761-4481-b2a7-03770da268f9";
    public static final String USER2_ID = "7dc8368a-8d7d-426a-8009-9e040211c109";
	private static final String WEEK_7_ID = "49de5eb9-9617-40fe-977e-0bb82a6073f9"; 
	
    private final static Logger LOG = LogManager.getLogger(BiltDataApplication.class);

    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(BiltDataApplication.class, args);

        /**
         * To get epoch times 
         * https://www.freeformatter.com/epoch-timestamp-to-date-converter.html
		 */
        
        /**
			Users
			[
			  {
			    "id": "0ac0d6d4-f761-4481-b2a7-03770da268f9",
			    "userName": "fcarta",
			    "firstName": "Frank",
			    "lastName": "Carta"
			  },
			  {
			    "id": "7dc8368a-8d7d-426a-8009-9e040211c109",
			    "userName": "ctruhe",
			    "firstName": "Cletus",
			    "lastName": "Truhe"
			  }
			]
         */
        final UserDao userDao = (UserDao) ctx.getBean("userDao");
        
        final User user = new User();
        user.setId(UUID.fromString(USER1_ID));
        user.setUserName("fcarta");
        user.setFirstName("Frank");
        user.setLastName("Carta");
        userDao.save(user);

        final User user2 = new User();
        user2.setId(UUID.fromString(USER2_ID));
        user2.setUserName("ctruhe");
        user2.setFirstName("Cletus");
        user2.setLastName("Truhe");
        userDao.save(user2);
        
		/**
			Teams
			[
			  {
			    "id": "50160fac-0c59-41af-b54c-f78e18b98fb1",
			    "name": "University of Utah",
			    "abbreviation": "Utah"
			  },
			  {
			    "id": "56d3e1c5-60ce-40c6-8ab7-c6732bbd89ed",
			    "name": "University of Southern California",
			    "abbreviation": "USC"
			  }
			]
		*/
        final TeamDao teamDao = (TeamDao) ctx.getBean("teamDao");
        
        final Team team1 = new Team();
        team1.setId(UUID.fromString("56d3e1c5-60ce-40c6-8ab7-c6732bbd89ed"));
        team1.setName("University of Southern California");
        team1.setAbbreviation("USC");
        teamDao.save(team1);
        
        final Team team2 = new Team();
        team2.setId(UUID.fromString("50160fac-0c59-41af-b54c-f78e18b98fb1"));
        team2.setName("University of Utah");
        team2.setAbbreviation("Utah");
        teamDao.save(team2);
        
        /**
			Week
			[
			  {
			    "id": "49de5eb9-9617-40fe-977e-0bb82a6073f9",
			    "name": "Week 7",
			    "startTimestamp": 1507446001,
			    "endTimestamp": 1508050800
			  }
			]
		*/
        final GameWeekDao gameWeekDao = (GameWeekDao) ctx.getBean("gameWeekDao");
        
        final GameWeek gameWeek7 = new GameWeek();
        gameWeek7.setId(UUID.fromString("49de5eb9-9617-40fe-977e-0bb82a6073f9"));
        gameWeek7.setName("Week 7");
        gameWeek7.setStartTimestamp(1507446001L);
        gameWeek7.setEndTimestamp(1508050800L);
        gameWeekDao.save(gameWeek7);

        /**
			Games 10/14/2017 @ 5:30pm PT
			[
			  {
			    "id": "842631c0-e81f-4179-bdca-bc5d9a32dd3e",
			    "homeTeamId": "56d3e1c5-60ce-40c6-8ab7-c6732bbd89ed",
			    "awayTeamId": "50160fac-0c59-41af-b54c-f78e18b98fb1",
			    "scoreMultplier": 2,
			    "startTimestamp": 1508027400
			  }
			]
		*/
        final GameDao gameDao = (GameDao) ctx.getBean("gameDao");
        
        final Game game = new Game();
        game.setId(UUID.fromString("842631c0-e81f-4179-bdca-bc5d9a32dd3e"));
        game.setHomeTeamId(team1.getId());
        game.setAwayTeamId(team2.getId());
        game.setScoreMultplier(Short.valueOf((short) 2));
        game.setStartTimestamp(1508027400L);
        gameDao.save(game);
        
		/**        
			Picks
			[
			  {
			    "id": "722769f7-c376-4408-8a3a-ae1ba06cc878",
			    "gameId": "842631c0-e81f-4179-bdca-bc5d9a32dd3e",
			    "teamId": "50160fac-0c59-41af-b54c-f78e18b98fb1",
			    "userId": "0ac0d6d4-f761-4481-b2a7-03770da268f9",
			    "lastModifiedTimestamp": 1508027399,
			    "result": 1
			  }
			]
		*/
        final GamePickDao gamePickDao = (GamePickDao) ctx.getBean("gamePickDao");
        
        final GamePick gamePick = new GamePick();
        gamePick.setId(UUID.fromString("722769f7-c376-4408-8a3a-ae1ba06cc878"));
        gamePick.setGameId(game.getId());
        gamePick.setTeamId(team2.getId());
        gamePick.setUserId(user.getId());
        gamePick.setLastModifiedTimestamp(1508027399L);
        gamePick.setResult(Short.valueOf((short) 1));
        gamePickDao.save(gamePick);

        /**
			GameStatus FAILED FOR NOW
			POST THIS NEEDS TO BE IMPLEMENTED
			{
			  "clock": 100,
			  "lastModifiedTimestamp": 1508027400,
			  "period": "FIRST",
			  "state": "NOW"
			}
         */
        final GameStatusDao gameStatusDao = (GameStatusDao) ctx.getBean("gameStatusDao");
        final GameStatus gameStatus = new GameStatus();
        gameStatus.setGameId(game.getId());
        gameStatus.setClock(100L);
        gameStatus.setPeriod(GamePeriod.FIRST);
        gameStatus.setState(GameState.NOW);
        gameStatus.setLastModifiedTimestamp(System.currentTimeMillis());
        gameStatusDao.save(gameStatus);
        
    }
}
