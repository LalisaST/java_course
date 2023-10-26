package edu.hw3.task1;

import java.util.HashMap;

public class Atbash {
    static HashMap<Character, Character> encoding = new HashMap<Character, Character>() {{
        put('A', 'Z');
        put('B', 'Y');
        put('C', 'X');
        put('D', 'W');
        put('E', 'V');
        put('F', 'U');
        put('G', 'T');
        put('H', 'S');
        put('I', 'R');
        put('J', 'Q');
        put('K', 'P');
        put('L', 'O');
        put('M', 'N');
        put('N', 'M');
        put('O', 'L');
        put('P', 'K');
        put('Q', 'J');
        put('R', 'I');
        put('S', 'H');
        put('T', 'G');
        put('U', 'F');
        put('V', 'E');
        put('W', 'D');
        put('X', 'C');
        put('Y', 'B');
        put('Z', 'A');
    }};

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

