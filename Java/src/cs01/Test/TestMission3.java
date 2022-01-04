package e7d849a05f7ba1ad554a5e92e5e2e6aa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestMission3 {
    Adder adder;

    @BeforeEach
    void init() {
        adder = new Adder();
    }

    @Test
    @DisplayName("Adder: ByteAdder")
    void byteadderTest() {
        assertArrayEquals(new boolean[]{false, false, false, true, false, true, false, false, true},
                adder.byteadder(new boolean[]{true, true, false, true, true, false, true, false},
                        new boolean[]{true, false, true, true, false, false, true, true}));
//        byteA  = [ 1, 1, 0, 1, 1, 0, 1, 0 ]
//        byteB  = [ 1, 0, 1, 1, 0, 0, 1, 1 ]
//        결과 = [ 0, 0, 0, 1, 0, 1, 0, 0, 1 ]

        assertArrayEquals(new boolean[]{false, true, true, true, false, true, true, true, false},
                adder.byteadder(new boolean[]{true, true, false, false, true, false, true, false},
                        new boolean[]{true, true, false, true, true, false, false, true}));

//        byteA  = [ 1, 1, 0, 0, 1, 0, 1, 0 ]
//        byteB  = [ 1, 1, 0, 1, 1, 0, 0, 1 ]
//        결과 = [ 0, 1, 1, 1, 0, 1, 1, 1, 0 ]
    }

    @Test
    @DisplayName("Adder: ByteAdder 4bit")
    void byteadder4bitTest(){
        assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true, false},
                adder.byteadder(new boolean[]{true, true, false, true},
                        new boolean[]{true, false, true, true, false, false, true, true}));
//        byteA  = [ 1, 1, 0, 1 ]
//        byteB  = [ 1, 0, 1, 1, 0, 0, 1, 1 ]
//        결과 = [ 0, 0, 0, 1, 1, 0, 1, 1, 0 ]
    }

    @Test
    @DisplayName("Adder: ByteAdder 16bit")
    void byteadder16bitTest(){
        assertArrayEquals(new boolean[]{false, false, false, true, true, false, true, true, true, false, true, true, false, false, true, true, false},
                adder.byteadder(new boolean[]{true, true, false, true},
                        new boolean[]{true, false, true, true, false, false, true, true, true, false, true, true, false, false, true, true}));
//        byteA  = [ 1, 1, 0, 1 ]
//        byteB  = [ 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1 ]
//           결과 = [ 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0 ]
    }

}
