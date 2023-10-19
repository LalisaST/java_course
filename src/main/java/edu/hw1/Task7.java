package edu.hw1;

public class Task7 {

    private Task7() {

    }

    public static int rotateLeft(int n, int shift) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (n < 0 || shift < 0) {
            return -1;
        }

        if (shift == 0) {
            return n;
        }


        String nStr = Integer.toBinaryString(n);
        char[] nChar = nStr.toCharArray();

        int newShift;
        if (shift > nChar.length) {
            newShift = shift % nChar.length;
        } else {
            newShift = shift;
        }

        for (int i = 0; i < newShift; i++) {
            char tmp = nChar[0];
            for (int j = 0; j < nChar.length - 1; j++) {
                nChar[j] = nChar[j + 1];
            }
            nChar[nChar.length - 1] = tmp;
        }

        return Integer.parseInt(new String(nChar), 2);
    }

    public static int rotateRight(int n, int shift) {
          String nStr = Integer.toBinaryString(n);
          int shiftL = nStr.length() - shift;
          return rotateLeft(n, shiftL);
    }
}
