package e7d849a05f7ba1ad554a5e92e5e2e6aa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import e7d849a05f7ba1ad554a5e92e5e2e6aa.Adder.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestMission2 {
    Adder adder;
    boolean[] result1 = {true, true}; // [1, 1]
    boolean[] result2 = {true, false}; // [1, 0]
    boolean[] result3 = {false, true}; // [0, 1]
    boolean[] result4 = {false, false}; // [0, 0]

    @BeforeEach
    void init() {
        adder = new Adder();
    }

    @Test
    @DisplayName("Adder : halfadder")
    void halfadderTest() {
        assertArrayEquals(result2, adder.halfadder(true, true));
        assertArrayEquals(result3, adder.halfadder(true, false));
        assertArrayEquals(result3, adder.halfadder(false, true));
        assertArrayEquals(result4, adder.halfadder(false, false));
    }

    @Test
    @DisplayName("Adder : fulladder")
    void fulladderTest(){
        assertArrayEquals(result1, adder.fulladder(true, true, true));
        assertArrayEquals(result2, adder.fulladder(false, true, true));
        assertArrayEquals(result2, adder.fulladder(true, false, true));
        assertArrayEquals(result3, adder.fulladder(false, false, true));
        assertArrayEquals(result2, adder.fulladder(true, true, false));
        assertArrayEquals(result3, adder.fulladder(false, true, false));
        assertArrayEquals(result3, adder.fulladder(true, false, false));
        assertArrayEquals(result4, adder.fulladder(false, false, false));
    }
}
