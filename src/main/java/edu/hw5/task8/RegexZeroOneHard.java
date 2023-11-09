package edu.hw5.task8;

public class RegexZeroOneHard {
    private RegexZeroOneHard() {}

    //нечетной длины
    public static boolean isOddLength(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^[01]([01][01])*$");
    }

    //начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
    public static boolean isStarts0WithAnOddLengthOr1WithAnEvenLength(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^(1[10]([01][01])*)$|^([0]([01][01])*)$");
    }

    //количество 0 кратно 3
    public static boolean isNumberOf0MultipleOf3(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^(1*01*01*01*)*$");
    }

    //любая строка, кроме 11 или 111
    public static boolean isAnyStringOther11Or111(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^(?!11$|111$)[01]+$");
    }

    //каждый нечетный символ равен 1
    public static boolean isEachOddCharacterEqual1(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^1$|^10$|^(1[01])+$|^1([01]?1)+$");
    }

    //содержит не менее двух 0 и не более одной 1
    public static boolean isContainsLeasTwo0AndMoreOne1(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^10{2,}$|^01{0,1}0$|^0{2,}1{0,1}0*$");
    }

    //нет последовательных 1
    public static boolean isNoConsecutive1(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        return str.matches("^(?![01]*11)[01]+$");
    }
}
