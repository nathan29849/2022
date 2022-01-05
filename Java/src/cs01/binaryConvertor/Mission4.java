import java.util.Arrays;
import java.util.Scanner;

public class Mission4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("십진수를 이진수로 변환");
        System.out.print("십진수를 입력하세요 >>> ");
        int decimal = Integer.parseInt(scanner.nextLine());

        Convertor convertor = new Convertor();
        boolean[] answer = convertor.dec2bin(decimal);
        System.out.print("결과 = ");
        System.out.println(Arrays.toString(answer));

        System.out.println("이진수로 십진수로 변환");
        System.out.print("이진수의 비트 수를 입력하세요(숫자로) >>> ");
        int bitNumber = Integer.parseInt(scanner.nextLine());
        boolean bit = false;
        boolean[] byteA = new boolean[bitNumber];
        for (int i = 0; i < bitNumber; i++) {
            System.out.print((i + 1) + "번째 이진수를 입력하세요 >>> ");
            bit = Boolean.parseBoolean(scanner.nextLine());
            byteA[i] = bit;
        }
        int answer2 = convertor.bin2dec(byteA);
        System.out.println(answer2);

        System.out.println("10진수 -> 2진수 -> 16진수");
        int decimal2 = Integer.parseInt(scanner.nextLine());
        String[] answer3 = convertor.dec2hex(decimal2);
        System.out.println(Arrays.toString(answer3));

    }
}
