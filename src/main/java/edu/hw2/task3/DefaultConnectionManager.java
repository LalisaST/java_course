package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    static int counter = 0;

    DefaultConnectionManager() {
        FaultyConnection.setPool();
        StableConnection.setPool();
        counter = 0;
    }

    @Override
    public Connection getConnection() {
        if (counter == 2) {
            counter = 0;
            return new FaultyConnection();
        }
        counter++;
        return new StableConnection();
    }
}
