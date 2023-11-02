package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();
    static int counter = 0;
    public static int pool = 0;

    public FaultyConnection() {
        pool++;
    }

    public static int getPool() {
        return pool;
    }

    public static void setPool() {
        pool = 0;
    }

    @Override
    public void execute(String command) {
        if (command == null) {
            throw new IllegalArgumentException();
        }

        if (counter == 1) {
            counter = 0;
            LOGGER.info("Command executed: " + command);
            return;
        }
        counter++;
        throw new ConnectionException();
    }

    @Override
    public void close() {
        pool--;
        LOGGER.info("Connection close");
    }
}
