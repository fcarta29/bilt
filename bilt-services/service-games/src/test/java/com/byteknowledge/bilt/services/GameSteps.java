package com.byteknowledge.bilt.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.byteknowledge.bilt.dao.GameDao;
import com.byteknowledge.bilt.dao.GamePickDao;
import com.byteknowledge.bilt.dao.GameStatusDao;
import com.byteknowledge.bilt.dao.GameWeekDao;
import com.byteknowledge.bilt.dao.TeamDao;
import com.byteknowledge.bilt.dao.UserDao;
import com.byteknowledge.bilt.dto.GameDto;
import com.byteknowledge.bilt.dto.GamePickDto;
import com.byteknowledge.bilt.model.Game;
import com.byteknowledge.bilt.model.GamePick;
import com.byteknowledge.bilt.model.GameStatus;
import com.byteknowledge.bilt.model.GameStatus.GamePeriod;
import com.byteknowledge.bilt.model.GameStatus.GameState;
import com.byteknowledge.bilt.model.GameWeek;
import com.byteknowledge.bilt.model.Team;
import com.byteknowledge.bilt.model.User;
import com.byteknowledge.bilt.services.game.service.GamePickService;
import com.byteknowledge.bilt.services.game.service.GameService;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import redis.embedded.RedisServerBuilder;

public class GameSteps extends SpringIntegrationTest {

    private static final Logger LOG = LogManager.getLogger(GameSteps.class);

    private static final String USER1_ID = "0ac0d6d4-f761-4481-b2a7-03770da268f9";
    private static final String USER2_ID = "7dc8368a-8d7d-426a-8009-9e040211c109";
    private static final String USER3_ID = "45f5d845-73e8-4a0b-a1cd-ce4ed0187d5a";
    private static final String TEAM1_ID = "56d3e1c5-60ce-40c6-8ab7-c6732bbd89ed";
    private static final String TEAM2_ID = "50160fac-0c59-41af-b54c-f78e18b98fb1";
    private static final String TEAM3_ID = "3fbb1b92-319a-4d3a-8feb-96e4264bd16c";
    private static final String TEAM4_ID = "9f348b20-e9e0-47ef-a8cb-08e4540ed716";
    private static final String WEEK7_ID = "49de5eb9-9617-40fe-977e-0bb82a6073f9";
    private static final String WEEK8_ID = "af046fb0-c790-453f-a279-cdeb8d4f65f2";
    private static final String GAME1_ID = "842631c0-e81f-4179-bdca-bc5d9a32dd3e";
    private static final String GAME2_ID = "cdf2b53d-4ecf-4bc4-b0ae-f4d46efc8fcf";
    private static final String GAMEPICK1_ID = "722769f7-c376-4408-8a3a-ae1ba06cc878";
    private static final String GAMEPICK2_ID = "d06eb513-c2d1-4330-9369-1a4938e5288f";
    private static final String GAMEPICK3_ID = "748d18fc-ef6f-427f-aef5-762f3324415f";

    @Autowired
    ApplicationContext context;

    @Autowired
    public JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    private GameService gameService;

    @Autowired
    private GamePickService gamePickService;

    private String today;
    private String userId;
    private String weekId;
    private Set<GamePickDto> gamePicks;
    private Map<GameDto, Set<GamePickDto>> gamePicksMap;

    @Before
    public void testSetup(final Scenario scenario) throws IOException, URISyntaxException {
        LOG.info("Starting Redis Embedded Server...");
        redisServer = new RedisServerBuilder().port(redisPort).build();
        redisServer.start();
        LOG.info("Redis Embedded Server Started");
    }

    @After
    public void testTeardown(final Scenario scenario) throws InterruptedException {
        LOG.info("Stoping Redis Embedded Server...");

        LOG.info("Destroying Redis Client Connections...");
        jedisConnectionFactory.destroy();
        LOG.info("Redis Client Connections destroyed");

        redisServer.stop();
        LOG.info("Redis Embedded Server Stoped...");
    }

    @Given("the embedded redis server is running")
    public void embeddedRedisServerRunning() {
        Assert.assertTrue(redisServer != null && redisServer.isActive());
    }

    @Given("the application data is loaded")
    public void applicationDataLoaded() {
        seedTestData();

        today = StringUtils.EMPTY;
        userId = StringUtils.EMPTY;
    }

    @Given("^today is (.+) of week (\\d+)$")
    public void today(final String today, final int weekNumber) {
        this.today = today;
        this.weekId = (weekNumber == 7) ? WEEK7_ID : WEEK8_ID;
    }

    @Given("^it is not gameday$")
    public void isItNotGameDay() {
        Assert.assertNotNull(today);
        Assert.assertNotEquals(StringUtils.EMPTY, today);
        Assert.assertNotEquals("Saturday", today);
    }

    @Given("^my member name is (.+)")
    public void isMemberName(final String memberName) {
        Assert.assertNotNull(memberName);
        Assert.assertNotEquals(StringUtils.EMPTY, memberName);

        switch (memberName) {
        case "fcarta":
            userId = USER1_ID;
            break;
        case "ctruhe":
            userId = USER2_ID;
            break;
        case "jtruhe":
            userId = USER3_ID;
            break;
        default:
            userId = "";
        }
    }

    @When("^I ask for the week (\\d+) game picks$")
    public void askingForWeekGamePicks(final int weekNumber) {
        String weekNumberId = StringUtils.EMPTY;
        switch (weekNumber) {
        case 7:
            weekNumberId = WEEK7_ID;
            break;
        case 8:
            weekNumberId = WEEK8_ID;
            break;
        default:
            throw new UnsupportedOperationException("Week number not implemented in test: " + weekNumber);
        }

        gamePicksMap = gamePickService.getGamePicksForWeek(UUID.fromString(weekNumberId));
    }

    @When("^I ask for my week (\\d+) game picks$")
    public void askingForMemberWeekGamePicks(final int weekNumber) {
        String weekNumberId = StringUtils.EMPTY;
        switch (weekNumber) {
        case 7:
            weekNumberId = WEEK7_ID;
            break;
        case 8:
            weekNumberId = WEEK8_ID;
            break;
        default:
            throw new UnsupportedOperationException("Week number not implemented in test: " + weekNumber);
        }

        gamePicks = gamePickService.getGamePicksForWeekAndUser(UUID.fromString(weekNumberId), UUID.fromString(userId));
    }

    @Then("^(\\d+) games? should be returned$")
    public void assertGamesAreReturned(final int numberOfGames) {
        Assert.assertEquals(numberOfGames, gamePicksMap.keySet().size());
    }

    @Then("^(\\d+) game picks? should be returned$")
    public void assertGamesPicksAreReturned(final int numberOfGamePicks) {
        int gamePicksCount = 0;
        for (Map.Entry<GameDto, Set<GamePickDto>> gamePicks : gamePicksMap.entrySet()) {
            gamePicksCount += gamePicks.getValue().size();
        }
        Assert.assertEquals(numberOfGamePicks, gamePicksCount);
    }

    @Then("^(\\d+) games? should be returned (.+)$")
    public void assertGamesAreReturnedForMember(final int numberOfGames, final String memberName) {
        Assert.assertEquals(numberOfGames, gamePicks.size());
    }

    @Then("^(\\d+) game picks? should be returned for (.+)$")
    public void assertGamesPicksAreReturnedForMember(final int numberOfGamePicks, final String memberName) {
        Assert.assertEquals(numberOfGamePicks, gamePicks.size());
    }

    private String gamesEndpoint = "";

    @Given("^I want to use the game api$")
    public void wantToUseGameAPI() {
        gamesEndpoint = gamesEndpoint();
        LOG.debug("Games endpoint: " + gamesEndpoint);
    }

    private Set<GameDto> games;

    @When("^I send the games get request$")
    public void sendGamesGet() {
        /*
         * [{ id=842631c0-e81f-4179-bdca-bc5d9a32dd3e,
         * gameName=842631c0-e81f-4179-bdca-bc5d9a32dd3e }, {
         * id=cdf2b53d-4ecf-4bc4-b0ae-f4d46efc8fcf,
         * gameName=cdf2b53d-4ecf-4bc4-b0ae-f4d46efc8fcf }]
         */
        games = new HashSet<GameDto>();
        Collection<LinkedHashMap> gamesData = get(gamesEndpoint);
        for (LinkedHashMap gameHashMap : gamesData) {
            LOG.info(gameHashMap);
            games.add(new GameDto.GameBuilder((String) gameHashMap.get("gameName")).setId((String) gameHashMap.get("id")).build());
        }
    }

    @Then("^I should have a games response$")
    public void gamesResponse() {
        LOG.debug("Games response: " + games.toString());
        Assert.assertNotNull(games);
        Assert.assertEquals(2, games.size());
    }

    private void seedTestData() {
        LOG.info("Populating test data...");
        final UserDao userDao = (UserDao) context.getBean("userDao");

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

        final User user3 = new User();
        user3.setId(UUID.fromString(USER3_ID));
        user3.setUserName("jtruhe");
        user3.setFirstName("James");
        user3.setLastName("Truhe");
        userDao.save(user3);

        final TeamDao teamDao = (TeamDao) context.getBean("teamDao");

        final Team team1 = new Team();
        team1.setId(UUID.fromString(TEAM1_ID));
        team1.setName("University of Southern California");
        team1.setAbbreviation("USC");
        teamDao.save(team1);

        final Team team2 = new Team();
        team2.setId(UUID.fromString(TEAM2_ID));
        team2.setName("University of Utah");
        team2.setAbbreviation("Utah");
        teamDao.save(team2);

        final Team team3 = new Team();
        team3.setId(UUID.fromString(TEAM3_ID));
        team3.setName("Pennsylvania State University");
        team3.setAbbreviation("Penn St");
        teamDao.save(team3);

        final Team team4 = new Team();
        team4.setId(UUID.fromString(TEAM4_ID));
        team4.setName("University of Michigan");
        team4.setAbbreviation("Mich");
        teamDao.save(team4);

        final GameWeekDao gameWeekDao = (GameWeekDao) context.getBean("gameWeekDao");

        final GameWeek gameWeek7 = new GameWeek();
        gameWeek7.setId(UUID.fromString(WEEK7_ID));
        gameWeek7.setName("Week 7");
        gameWeek7.setStartTimestamp(1507446001L);
        gameWeek7.setEndTimestamp(1508050800L);
        gameWeekDao.save(gameWeek7);

        final GameDao gameDao = (GameDao) context.getBean("gameDao");

        final Game game = new Game();
        game.setId(UUID.fromString(GAME1_ID));
        game.setHomeTeamId(team1.getId());
        game.setAwayTeamId(team2.getId());
        game.setScoreMultplier(Short.valueOf((short) 2));
        game.setStartTimestamp(1508027400L);
        gameDao.save(game);

        final Game game2 = new Game();
        game2.setId(UUID.fromString(GAME2_ID));
        game2.setHomeTeamId(team3.getId());
        game2.setAwayTeamId(team4.getId());
        game2.setScoreMultplier(Short.valueOf((short) 2));
        game2.setStartTimestamp(1508027400L);
        gameDao.save(game2);

        final GamePickDao gamePickDao = (GamePickDao) context.getBean("gamePickDao");

        final GamePick gamePick = new GamePick();
        gamePick.setId(UUID.fromString(GAMEPICK1_ID));
        gamePick.setGameId(game.getId());
        gamePick.setTeamId(team2.getId());
        gamePick.setUserId(user.getId());
        gamePick.setLastModifiedTimestamp(1508027399L);
        gamePick.setResult(Short.valueOf((short) 1));
        gamePickDao.save(gamePick);

        final GamePick gamePick2 = new GamePick();
        gamePick2.setId(UUID.fromString(GAMEPICK2_ID));
        gamePick2.setGameId(game.getId());
        gamePick2.setTeamId(team2.getId());
        gamePick2.setUserId(user2.getId());
        gamePick2.setLastModifiedTimestamp(1508027400L);
        gamePick2.setResult(Short.valueOf((short) 1));
        gamePickDao.save(gamePick2);

        final GamePick gamePick3 = new GamePick();
        gamePick3.setId(UUID.fromString(GAMEPICK3_ID));
        gamePick3.setGameId(game2.getId());
        gamePick3.setTeamId(team3.getId());
        gamePick3.setUserId(user.getId());
        gamePick3.setLastModifiedTimestamp(1508027400L);
        gamePick3.setResult(Short.valueOf((short) 1));
        gamePickDao.save(gamePick3);

        final GameStatusDao gameStatusDao = (GameStatusDao) context.getBean("gameStatusDao");
        final GameStatus gameStatus = new GameStatus();
        gameStatus.setGameId(game.getId());
        gameStatus.setClock(100L);
        gameStatus.setPeriod(GamePeriod.FIRST);
        gameStatus.setState(GameState.NOW);
        gameStatus.setLastModifiedTimestamp(System.currentTimeMillis());
        gameStatusDao.save(gameStatus);

        LOG.info("Finished populating test data...");
    }
}
