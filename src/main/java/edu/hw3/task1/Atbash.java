package edu.hw3.task1;

import java.util.HashMap;
import java.util.Map;

public class Atbash {
    static Map<Character, Character> encoding = new HashMap<>(){};

    static {
        encoding.put('A', 'Z');
        encoding.put('B', 'Y');
        encoding.put('C', 'X');
        encoding.put('D', 'W');
        encoding.put('E', 'V');
        encoding.put('F', 'U');
        encoding.put('G', 'T');
        encoding.put('H', 'S');
        encoding.put('I', 'R');
        encoding.put('J', 'Q');
        encoding.put('K', 'P');
        encoding.put('L', 'O');
        encoding.put('M', 'N');
        encoding.put('N', 'M');
        encoding.put('O', 'L');
        encoding.put('P', 'K');
        encoding.put('Q', 'J');
        encoding.put('R', 'I');
        encoding.put('S', 'H');
        encoding.put('T', 'G');
        encoding.put('U', 'F');
        encoding.put('V', 'E');
        encoding.put('W', 'D');
        encoding.put('X', 'C');
        encoding.put('Y', 'B');
        encoding.put('Z', 'A');
    }

    private Atbash() {

    }

    public static String atbash(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder cipher = new StringBuilder();

        for (char letter : str.toCharArray()) {

            if (encoding.containsKey(Character.toUpperCase(letter))) {
                if (Character.isUpperCase(letter)) {
                    cipher.append(encoding.get(letter));
                } else {
                    encodingLowerCase(letter, cipher);
                }
            } else {
                cipher.append(letter);
            }
        }

        return cipher.toString();
    }

    private static void encodingLowerCase(char letter, StringBuilder stringBuilder) {
        char upperLetter = Character.toUpperCase(letter);
        char lowerLetter;

        if (encoding.containsKey(upperLetter)) {
            lowerLetter = Character.toLowerCase(encoding.get(upperLetter));
            stringBuilder.append(lowerLetter);
        }
    }
}

