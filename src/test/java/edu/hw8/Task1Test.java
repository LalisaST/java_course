package edu.hw8;

import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task1Test {

    @Test
    @DisplayName("Передача некорректных аргументов в Сlient")
    void enteringIncorrectArgumentsInClient() {
        assertThatThrownBy(() -> Client.start("")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Client.start(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Client.start("  ")).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("Проверка получения цитаты")
    void checkingReceiptQuote() throws InterruptedException {

        Thread thread = new Thread(Server::start);
        thread.start();

        String result = Client.start("личности");

        Server.stop();
        thread.join();

        assertThat(result).isEqualTo("Сервер: Не переходи на личности там, где их нет");
    }

    @Test
    @DisplayName("Ввод неизвестного ключевого слова")
    void enteringUnknownKeyword() throws InterruptedException {

        Thread thread = new Thread(Server::start);
        thread.start();

        String result = Client.start("a");

        assertThat(result).isEqualTo("Сервер: Неизветсное ключевое слово");

        Server.stop();
        thread.join();
    }

    @Test
    @DisplayName("Проверка нескольких подключений")
    void checkingMultipleConnections() throws InterruptedException {

        Thread thread = new Thread(Server::start);
        thread.start();

        String result1 = Client.start("глупый");
        String result2 = Client.start("и");

        Server.stop();
        thread.join();

        assertThat(result1).isEqualTo("Сервер: А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.");
        assertThat(result2).isEqualTo("Сервер: Неизветсное ключевое слово");
    }

}
