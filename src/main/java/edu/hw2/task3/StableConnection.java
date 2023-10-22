package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    public static int pool = 0;

    public StableConnection() {
        pool++;
    }

    public static int getPool() {
        return pool;
    }


    @Override
    public void execute(String command) {
        if (command == null) {
            throw new IllegalArgumentException();
        }

        LOGGER.info("Command executed: " + command);
    }

    @Override
    public void close() {
        pool--;
        LOGGER.info("Connection close");
    }
}
