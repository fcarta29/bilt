package com.byteknowledge.bilt.data;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.byteknowledge.bilt.dao.UserDao;
import com.byteknowledge.bilt.model.User;
import com.byteknowledge.bilt.service.Service;

@SpringBootApplication
public class BiltDataApplication {

    private final static Logger LOG = Logger.getLogger(BiltDataApplication.class);

    public static void main(String[] args) {
        final ApplicationContext ctx = SpringApplication.run(BiltDataApplication.class, args);

        final UserDao userDao = (UserDao) ctx.getBean("userDao");

        final User user = new User();
        user.setId(UUID.fromString(Service.USER1_ID));
        user.setUserName("fcarta");
        user.setFirstName("Frank");
        user.setLastName("Carta");
        userDao.save(user);

        final User user2 = new User();
        user2.setId(UUID.fromString(Service.USER2_ID));
        user2.setUserName("awerhane");
        user2.setFirstName("Andrew");
        user2.setLastName("Werhane");
        userDao.save(user2);
        
        
        /**
         * 
To get epoch times 
https://www.freeformatter.com/epoch-timestamp-to-date-converter.html

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
    "userName": "awerhane",
    "firstName": "Andrew",
    "lastName": "Werhane"
  }
]

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

Week
[
  {
    "id": "49de5eb9-9617-40fe-977e-0bb82a6073f9",
    "name": "Week 7",
    "startTimestamp": 1507446001,
    "endTimestamp": 1508050800
  }
]

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

GameStatus FAILED FOR NOW
POST THIS NEEDS TO BE IMPLEMENTED
{
  "clock": 100,
  "lastModifiedTimestamp": 1508027400,
  "period": "FIRST",
  "state": "NOW"
}

         */
        

        LOG.debug(userDao.get(UUID.fromString(Service.USER1_ID)));
        LOG.debug(userDao.get(UUID.fromString(Service.USER2_ID)));
    }
}
