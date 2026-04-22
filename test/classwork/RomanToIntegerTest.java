package classwork;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RomanToIntegerTest {
    @Test
    void convertsRomanNumeralsToIntegers() {
        assertEquals(3, RomanToInteger.romanToInt("III"));
        assertEquals(4, RomanToInteger.romanToInt("IV"));
        assertEquals(9, RomanToInteger.romanToInt("IX"));
        assertEquals(58, RomanToInteger.romanToInt("LVIII"));
        assertEquals(1994, RomanToInteger.romanToInt("MCMXCIV"));
    }
}
