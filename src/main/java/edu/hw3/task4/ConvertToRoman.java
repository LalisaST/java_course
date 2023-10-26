package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConvertToRoman {
    private final static int THOUSAND = 1000;
    private final static int NINE_HUNDRED = 900;
    private final static int FIVE_HUNDRED = 500;
    private final static int FOUR_HUNDRED = 400;
    private final static int ONE_HUNDRED = 100;
    private final static int NINETY = 90;
    private final static int FIFTY = 50;
    private final static int FORTY = 40;
    private final static int TEN = 10;
    private final static int NINE = 9;
    private final static int FIVE = 5;
    private final static int FOUR = 3;
    private final static int ONE = 1;
    private final static int MAX_VALUE = 3999;

    private ConvertToRoman() {

    }

    public static String convertToRoman(int number) {
        if (number < 1 || number > MAX_VALUE) {
            throw new IllegalArgumentException();
        }

        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
        map.put(THOUSAND, "M");
        map.put(NINE_HUNDRED, "CM");
        map.put(FIVE_HUNDRED, "D");
        map.put(FOUR_HUNDRED, "CD");
        map.put(ONE_HUNDRED, "C");
        map.put(NINETY, "XC");
        map.put(FIFTY, "L");
        map.put(FORTY, "XL");
        map.put(TEN, "X");
        map.put(NINE, "IX");
        map.put(FIVE, "V");
        map.put(FOUR, "IV");
        map.put(ONE, "I");

        StringBuilder result = new StringBuilder();
        int buff = number;

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            int k = entry.getKey();
            while (buff >= k) {
                result.append(map.get(k));
                buff = buff - entry.getKey();
            }
        }
        return result.toString();
    }
}
