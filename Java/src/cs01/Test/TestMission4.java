package e7d849a05f7ba1ad554a5e92e5e2e6aa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMission4 {
    Convertor convertor;

    @BeforeEach
    void init() {
        convertor = new Convertor();
    }

    @Test
    @DisplayName("Convertor : dec2bin")
    void dec2binTest() {
        assertArrayEquals(new boolean[]{false, true, false, true},
                convertor.dec2bin(10));

        assertArrayEquals(new boolean[]{true, false, true, true, false, true, false, true},
                convertor.dec2bin(173));

        assertArrayEquals(new boolean[]{false, true, false, true, false, false, false, false, false, false, false, false, false, false, false, true},
                convertor.dec2bin(32778));

    }

    @Test
    @DisplayName("Convertor : bin2dec")
    void bin2decTest() {
        assertEquals(10, convertor.bin2dec(new boolean[]{false, true, false, true}));
        assertEquals(173, convertor.bin2dec(new boolean[]{true, false, true, true, false, true, false, true}));
    }

    @Test
    @DisplayName("Convertor : bin2dec 16bit")
    void bin2decTest16bit() {
        assertEquals(32778, convertor.bin2dec(new boolean[]{false, true, false, true, false, false, false, false, false, false, false, false, false, false, false, true}));
    }

    @Test
    @DisplayName("Convertor : dec2hex")
    void dec2hexTest() {
        assertArrayEquals(new String[]{"d", "a"}, convertor.dec2hex(173));
        assertArrayEquals(new String[]{"4", "b"}, convertor.dec2hex(180));
    }

}
