import java.util.Arrays;
import java.util.Scanner;

public class Mission3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("byteA는 몇 비트로 이루어져 있습니까?(숫자만 입력하세요) : ");
        int sizeA = Integer.parseInt(scanner.nextLine());

        System.out.print("byteB는 몇 비트로 이루어져 있습니까?(숫자만 입력하세요) : ");
        int sizeB = Integer.parseInt(scanner.nextLine());

        boolean[] byteA = new boolean[sizeA];
        boolean[] byteB = new boolean[sizeB];
        System.out.println();
        System.out.println();

        System.out.println("첫 번째 boolean 배열의 값을 입력합니다.");
        String temp;
        Boolean element;
        for (int i = 0; i < byteA.length; i++) {
            System.out.print((i + 1) + "번 째 boolean 값을 입력하세요 (true/false) >>> ");
            temp = scanner.nextLine();
            if (checkBoolean(temp)) {
                element = Boolean.parseBoolean(temp);
                byteA[i] = element;
            } else {
                i--;
            }
        }

        System.out.println("두 번째 boolean 배열의 값을 입력합니다.");
        for (int i = 0; i < byteB.length; i++) {
            System.out.print((i + 1) + "번 째 boolean 값을 입력하세요 (true/false) >>> ");
            temp = scanner.nextLine();
            if (checkBoolean(temp)) {
                element = Boolean.parseBoolean(temp);
                byteB[i] = element;
            } else {
                i--;
            }
        }


        e7d849a05f7ba1ad554a5e92e5e2e6aa.Adder adder = new e7d849a05f7ba1ad554a5e92e5e2e6aa.Adder();
        boolean[] answer = adder.byteadder(byteA, byteB);
        System.out.println("byteA = " + Arrays.toString(byteA));
        System.out.println("byteB = " + Arrays.toString(byteB));
        System.out.print("결과 : ");
        System.out.println(Arrays.toString(answer));
    }

    static boolean checkBoolean(String s) {
        if (s.equals("true") || s.equals("false")) {
            return true;
        }
        System.out.println("잘못된 입력입니다. 다시 입력해 주세요");
        return false;
    }

}