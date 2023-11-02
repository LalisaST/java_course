package edu.hw2.task3;

public class FaultyConnectionManager implements ConnectionManager {

    FaultyConnectionManager() {
        FaultyConnection.setPool();
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection();
    }
}
