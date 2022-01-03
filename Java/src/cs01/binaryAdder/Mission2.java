import java.util.Arrays;
import java.util.Scanner;
import cs01.binaryAdder.*;

public class Mission2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("bitA = ");
        boolean bitA = scanner.nextBoolean();
        System.out.print("bitB = ");
        boolean bitB = scanner.nextBoolean();
        System.out.print("carry = ");
        boolean carryBit = scanner.nextBoolean();

        Adder adder = new Adder();
        System.out.println("###### halfadder ######");
        boolean[] resultArr = adder.halfadder(bitA, bitB);
        System.out.print("결과 = "+Arrays.toString(resultArr));
        System.out.println();
        System.out.println("###### fulladder ######");
        boolean[] resultArr2 = adder.fulladder(bitA, bitB, carryBit);
        System.out.print("결과 = "+Arrays.toString(resultArr2));
    }
}

