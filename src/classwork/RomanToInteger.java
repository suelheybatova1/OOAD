package classwork;

public class RomanToInteger {

    public static void main(String[] args) {
        runTestCases();
    }

    // Converts a Roman number to a normal integer number.
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

    // Returns the integer value of one Roman symbol.
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

    // Test cases for checking the romanToInt method.
    public static void runTestCases() {
        int passed = 0;
        int failed = 0;

        if (test("I", 1)) passed++; else failed++;
        if (test("II", 2)) passed++; else failed++;
        if (test("III", 3)) passed++; else failed++;
        if (test("IV", 4)) passed++; else failed++;
        if (test("V", 5)) passed++; else failed++;
        if (test("IX", 9)) passed++; else failed++;
        if (test("X", 10)) passed++; else failed++;
        if (test("XL", 40)) passed++; else failed++;
        if (test("L", 50)) passed++; else failed++;
        if (test("XC", 90)) passed++; else failed++;
        if (test("C", 100)) passed++; else failed++;
        if (test("CD", 400)) passed++; else failed++;
        if (test("D", 500)) passed++; else failed++;
        if (test("CM", 900)) passed++; else failed++;
        if (test("M", 1000)) passed++; else failed++;
        if (test("LVIII", 58)) passed++; else failed++;
        if (test("MCMXCIV", 1994)) passed++; else failed++;

        System.out.println();
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    public static boolean test(String input, int expected) {
        int result = romanToInt(input);

        if (result == expected) {
            System.out.println("PASS: " + input + " -> " + result);
            return true;
        } else {
            System.out.println("FAIL: " + input + " -> " + result + " (expected " + expected + ")");
            return false;
        }
    }
}
