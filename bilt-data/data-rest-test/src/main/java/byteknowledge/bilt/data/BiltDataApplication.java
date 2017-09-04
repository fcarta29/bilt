package com.byteknowledge.bilt.data;

import java.util.Calendar;
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

        LOG.debug(userDao.get(UUID.fromString(Service.USER1_ID)));
        LOG.debug(userDao.get(UUID.fromString(Service.USER2_ID)));
    }
}
