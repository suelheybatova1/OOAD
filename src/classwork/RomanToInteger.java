package classwork;

public class RomanToInteger {

    public static void main(String[] args) {

        // test cases
        test("III", 3);
        test("IV", 4);
        test("IX", 9);
        test("LVIII", 58);
        test("MCMXCIV", 1994);
    }

    // метод перевода
    public static int romanToInt(String s) {
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int current = getValue(s.charAt(i));

            if (i < s.length() - 1 && current < getValue(s.charAt(i + 1))) {
                result -= current;
            } else {
                result += current;
            }
        }

        return result;
    }

    // значения символов
    public static int getValue(char c) {
        if (c == 'I') return 1;
        if (c == 'V') return 5;
        if (c == 'X') return 10;
        if (c == 'L') return 50;
        if (c == 'C') return 100;
        if (c == 'D') return 500;
        if (c == 'M') return 1000;
        return 0;
    }

    // тест кейсы
    public static void test(String input, int expected) {
        int result = romanToInt(input);

        if (result == expected) {
            System.out.println("PASS: " + input + " -> " + result);
        } else {
            System.out.println("FAIL: " + input + " -> " + result + " (expected " + expected + ")");
        }
    }
}