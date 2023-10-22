package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ClosingConnectionTest {
    @Test
    @DisplayName("Закрытие проблемного соединения")
    void closingFaultyConnection() {

        FaultyConnectionManager faultyConnectionManager = new FaultyConnectionManager();
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(faultyConnectionManager, 2);

        assertThat(FaultyConnection.getPool()).isEqualTo(0);
        popularCommandExecutor.updatePackages();
        assertThat(FaultyConnection.getPool()).isEqualTo(0);
    }

    @Test
    @DisplayName("Закрытие стабильного соединения")
    void closingStableConnection() {

        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(defaultConnectionManager, 1);

        assertThat(StableConnection.getPool()).isEqualTo(0);
        popularCommandExecutor.updatePackages();
        assertThat(StableConnection.getPool()).isEqualTo(0);

    }
}
