package e7d849a05f7ba1ad554a5e92e5e2e6aa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Convertor {
    public boolean[] dec2bin(int decimal) {
        ArrayList<Boolean> booleanArray = new ArrayList<>();
        int i = 0;
        while ((decimal / 2) >= 1) {
            // 2로 나눈 몫이 1보다 크면 반복문 계속 ~
            booleanArray.add(decCheck(decimal));
            i++;
            decimal /= 2;
        }
        booleanArray.add(decCheck(decimal));

        // Boolean -> boolean
        boolean[] temp = new boolean[booleanArray.size()];
        int j = 0;
        for (boolean b : booleanArray) {
            temp[j] = b;
            j++;
        }

        boolean[] answer = Arrays.copyOfRange(temp, 0, i + 1);
        return answer;
    }


    public int bin2dec(boolean[] bin) {
        int answer = 0;
        int power = 1;
//        for (int i = 0; i < bin.length; i++) {
//            answer += ((binCheck(bin[i])) * power);
//            power *= 2;
//        }

        for (boolean b : bin) { // for each문으로 표현
            answer += (binCheck(b) * power);
            power *= 2;
        }
        return answer;
    }

    static boolean decCheck(int dec) { // 10진수를 2로 나눈 나머지 0, 1에 따라 false, true return
        if (dec % 2 == 0) {
            return false;
        }
        return true;
    }

    static int binCheck(boolean bin) { // boolean을 int형으로
        if (bin) {
            return 1;
        }
        return 0;
    }

    public String[] dec2hex(int decimal) {
        boolean[] bin = dec2bin(decimal);
        String[] answer = new String[bin.length / 4]; // 2진수 4개씩 묶어 더함
        int k = 0;

        /* HashMap 사용해보기 */
        HashMap<Integer, String> map = new HashMap<Integer, String>() {{
            put(10, "a");
            put(11, "b");
            put(12, "c");
            put(13, "d");
            put(14, "e");
            put(15, "f");
        }};

        for (int i = 0; i < bin.length; i += 4) { // 4개씩 묶음으로 계산
            int temp = 0;
            int power = 1;
            for (int j = 0; j < 4; j++) {
                if (i + j < bin.length) {
                    temp += binCheck(bin[i + j]) * power;
                    power *= 2;
                }
            }
            String tempString = "";
            if (map.get(temp) != null) {
                tempString = map.get(temp);
            } else {
                tempString += temp;
            }
            answer[k] = tempString;
            k++;
        }
        return answer;
    }
}
