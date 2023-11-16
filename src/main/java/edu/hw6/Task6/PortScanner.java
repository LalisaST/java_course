package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class PortScanner {
    private final static Logger LOGGER = LogManager.getLogger();

    static Map<Integer, String> knownPorts = new HashMap<>();
    static final String TCP = "TCP";
    static final String UDP = "UDP";

    static {
        knownPorts.put(135, "EPMAP");
        knownPorts.put(137, "Служба имен NetBIOS");
        knownPorts.put(138, "Служба датаграмм NetBIOS");
        knownPorts.put(139, "Служба сеансов NetBIOS");
        knownPorts.put(445, "Microsoft-DS Active Directory");
        knownPorts.put(843, "Adobe Flash");
        knownPorts.put(1900, "SSDP");
        knownPorts.put(3702, "Динамическое обнаружение веб-служб");
        knownPorts.put(5353, "Многоадресный DNS");
        knownPorts.put(5355, "LLMNR");
        knownPorts.put(17500, "Dropbox");
        knownPorts.put(27017, "MongoDB");
    }

    private PortScanner() {
    }

    public static void portScanning() {


        for (int i = 0; i <= 49151; i++) {
            try (ServerSocket serverSocket = new ServerSocket(i)) {
                serverSocket.close();
                printInfo(true, i, TCP);
            } catch (IOException e) {
                printInfo(false, i, TCP);
            }

            try (DatagramSocket datagramSocket = new DatagramSocket(i)) {
                datagramSocket.close();
                printInfo(true, i, UDP);
            } catch (IOException e) {
                printInfo(false, i, UDP);
            }
        }
    }

    private static void printInfo(boolean flag, int i, String protocol) {
        String beg = "Протокол: " + protocol + "\tПорт: " + i + "\tСтатус: ";
        String end  = "\tСервис: " + knownPorts.getOrDefault(i, "");
        if (flag) {
            LOGGER.info(beg + "Свободен" + end);
        } else {
            LOGGER.info(beg + "Занят" + end);
        }
    }
}
