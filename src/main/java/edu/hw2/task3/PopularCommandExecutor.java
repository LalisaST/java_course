package edu.hw2.task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    public void tryExecute(String command) {

        int attempts = maxAttempts;

        while (attempts != 0) {

            try (Connection connection = manager.getConnection()) {
                if (command == null) {
                    throw new IllegalArgumentException();
                }
                connection.execute(command);
                return;
            } catch (Exception e) {
                attempts--;

                if (attempts == 0) {
                    throw new ConnectionException(e.getMessage(), e);
                }
            }
        }
    }
}
