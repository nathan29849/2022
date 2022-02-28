import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static binaryAdder.Mission1.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMission1 {
    @Test
    @DisplayName("GATE : AND")
    void andTest(){
        assertEquals(true, and(true, true));
        assertEquals(false, and(true, false));
        assertEquals(false, and(false, true));
        assertEquals(false, and(false, false));
    }

    @Test
    @DisplayName("GATE : OR")
    void orTest(){
        assertEquals(true, or(true, true));
        assertEquals(true, or(true, false));
        assertEquals(true, or(false, true));
        assertEquals(false, or(false, false));
    }

    @Test
    @DisplayName("GATE : NAND")
    void nandTest(){
        assertEquals(false, nand(true, true));
        assertEquals(true, nand(true, false));
        assertEquals(true, nand(false, true));
        assertEquals(true, nand(false, false));
    }

    @Test
    @DisplayName("GATE : XOR")
    void xorTest(){
        assertEquals(false, xor(true, true));
        assertEquals(true, xor(true, false));
        assertEquals(true, xor(false, true));
        assertEquals(false, xor(false, false));
    }


}
