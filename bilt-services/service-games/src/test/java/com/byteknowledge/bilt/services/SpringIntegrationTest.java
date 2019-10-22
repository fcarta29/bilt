package com.byteknowledge.bilt.services;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.byteknowledge.bilt.services.game.BiltGameApplication;

import redis.embedded.RedisServer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BiltGameApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@TestPropertySource({ "classpath:/test-redis.properties" })
public abstract class SpringIntegrationTest {

    private Logger LOG = LogManager.getLogger(SpringIntegrationTest.class);

    protected @Value("${redis.host-name}") String redisHostName;
    protected @Value("${redis.port}") int redisPort;

    protected RedisServer redisServer;

    private final String SERVER_URL = "http://localhost";
    private final String GAMES_ENDPOINT = "/api/games";

    private RestTemplate restTemplate;

    @LocalServerPort
    protected int port;

    public SpringIntegrationTest() {
        restTemplate = new RestTemplate();
    }

    protected String gamesEndpoint() {
        return SERVER_URL + ":" + port + GAMES_ENDPOINT;
    }

    protected int put(final String endpoint, final String content) {
        return restTemplate.postForEntity(endpoint, content, Void.class).getStatusCode().value();
    }

    protected Collection<LinkedHashMap> get(final String endpoint) {
        return (Collection<LinkedHashMap>) restTemplate.getForEntity(endpoint, Collection.class).getBody();
    }

    void clean(final String endpoint) {
        restTemplate.delete(endpoint);
    }

}
