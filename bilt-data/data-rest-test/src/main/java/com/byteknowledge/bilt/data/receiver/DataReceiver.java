package com.byteknowledge.bilt.data.receiver;

import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReceiver {

    private static final Logger LOGGER = LogManager.getLogger(DataReceiver.class);

	private ObjectMapper mapper = new ObjectMapper();

    private CountDownLatch latch;

    @Autowired
    public DataReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");

        /*try {

        } catch (Exception ex) {
        	LOGGER.error("Failed to update tile placement ", ex);
        }*/

        latch.countDown();
    }
}
