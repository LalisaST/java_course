package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {

    @Test
    @DisplayName("Передача null в FaultyConnection")
    void passingNullFaultyConnection() {
        assertThatThrownBy(() -> {

            FaultyConnection faultyConnection = new FaultyConnection();
            faultyConnection.execute(null);

        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("Передача null в StableConnection")
    void passingNullStableConnection() {
        assertThatThrownBy(() -> {

            StableConnection stableConnection = new StableConnection();
            stableConnection.execute(null);

        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("Превышение количества попыток")
    void exceedingNumberAttempts() {
        assertThatThrownBy(() -> {

            FaultyConnectionManager faultyConnectionManager = new FaultyConnectionManager();
            PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(faultyConnectionManager, 1);
            popularCommandExecutor.updatePackages();

        }).isInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName("Проверка работы DefaultConnectionManager")
    void checkingWorkDefaultConnectionManager() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
        Connection connection = defaultConnectionManager.getConnection();

        boolean flag = connection instanceof StableConnection;
        assertThat(flag).isEqualTo(true);

        defaultConnectionManager.getConnection();
        connection = defaultConnectionManager.getConnection();

        flag = connection instanceof FaultyConnection;
        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка работы FaultyConnectionManager")
    void checkingWorkFaultyConnectionManager() {
        FaultyConnectionManager faultyConnectionManager = new FaultyConnectionManager();
        Connection connection = faultyConnectionManager.getConnection();

        boolean flag = connection instanceof FaultyConnection;
        assertThat(flag).isEqualTo(true);

        faultyConnectionManager.getConnection();
        connection = faultyConnectionManager.getConnection();

        flag = connection instanceof FaultyConnection;
        assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("Проверка работы FaultyConnection")
    void checkingWorkFaultyConnection() {
        FaultyConnection faultyConnection = new FaultyConnection();

        assertThatThrownBy(() -> faultyConnection.execute("command")).isInstanceOf(ConnectionException.class);

        assertThatNoException().isThrownBy(() -> faultyConnection.execute("command"));
    }

    @Test
    @DisplayName("Проверка работы StableConnection")
    void checkingWorkStableConnection() {
        StableConnection stableConnection = new StableConnection();

        assertThatNoException().isThrownBy(() -> stableConnection.execute("command"));
        assertThatNoException().isThrownBy(() -> stableConnection.execute("command"));
    }

    @Test
    @DisplayName("Проверка параметра cause")
    void CheckingCauseParameter() {

        try {
            FaultyConnectionManager faultyConnectionManager = new FaultyConnectionManager();
            PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(faultyConnectionManager, 1);
            popularCommandExecutor.tryExecute(null);
        } catch (Exception e) {
            assertThat(e).hasCauseInstanceOf(IllegalArgumentException.class);
        }

    }

}

