package e7d849a05f7ba1ad554a5e92e5e2e6aa;

import java.util.Arrays;

public class Convertor {
    public boolean[] dec2bin(int decimal){
        boolean[] temp = new boolean[16];
        int i = 0;
        while(true) {
            // 2로 나눈 몫이 1보다 크면 반복문 계속 ~
            if((decimal/2) >= 1){
                temp[i] = decCheck(decimal);
                i++;
                decimal /= 2;
            } else {
                break;
            }

        }
        temp[i] = decCheck(decimal);

        boolean[] answer = Arrays.copyOfRange(temp, 0, i+1);


        return answer;
    }


    public int bin2dec(boolean[] bin){
        int answer = 0;
        int power = 1;
        for(int i = 0; i < bin.length; i++){
            answer += ((binCheck(bin[i]))*power);
            power *= 2;
        }
        return answer;
    }

    static boolean decCheck(int dec){ // 10진수를 2로 나눈 나머지 0, 1에 따라 false, true return
        if(dec%2 == 0){ return false; }
        return true;
    }

    static int binCheck(boolean bin){ // boolean을 int형으로
        if(bin){ return 1; }
        return 0;
    }

    public String[] dec2hex(int decimal){
        boolean[] bin = dec2bin(decimal);
        String[] answer = new String[bin.length/4+1]; // 2진수 4개씩 묶어 더한 것을 하나씩 추가하는 느낌
        int k = 0;
        for(int i=0; i<bin.length; i+=4){ // 4개씩 묶음으로 계산
            int temp = 0;
            int power = 1;
            for(int j=0; j<4; j++){
                if(i+j < bin.length){
                    temp += binCheck(bin[i+j]) * power;
                    power *= 2;
                }
            }
            String tempString = "";
            if(temp<10){
                tempString += temp;
            } else{
                switch (temp){
                    case 10:
                        tempString = "a";
                        break;
                    case 11:
                        tempString = "b";
                        break;
                    case 12:
                        tempString = "c";
                        break;
                    case 13:
                        tempString = "d";
                        break;
                    case 14:
                        tempString = "e";
                        break;
                    default:
                        tempString = "f";
                }

            }
            answer[k] = tempString;
            k++;
        }
        return answer;
    }
}
