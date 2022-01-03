package e7d849a05f7ba1ad554a5e92e5e2e6aa;
import java.util.ArrayList;

public class Adder {
        public boolean sum(boolean bitA, boolean bitB){
            // 값이 다를 때만 true 반환 - ex. 0, 1 일때 1로 남아있기 위함
            return Mission1.xor(bitA, bitB);
        }

        public boolean carry(boolean bitA, boolean bitB){
            // 둘다 참(=1)일 때만 true 반환 - ex. 1, 1일 때 올림 수 1이 생김
            return Mission1.and(bitA, bitB);
        }

        public boolean[] halfadder(boolean bitA, boolean bitB){
            boolean carryResult = carry(bitA, bitB);
            boolean sumResult = sum(bitA, bitB);
            boolean[] answer = {carryResult, sumResult};
            return answer;
        }

        public boolean[] fulladder(boolean bitA, boolean bitB, boolean carry){
            boolean[] tempArr = halfadder(bitA, bitB); // 반쪽 덧셈 함수를 통해 연산
            boolean tempResult = sum(tempArr[1], carry); // 반쪽 덧셈 결과 중 덧셈 결과를 새로운 올림수(carry)와 sum: 남아있는 수 확인
            boolean newCarry = carry(tempArr[1], carry); // 반쪽 덧셈 결과 중 덧셈 결과를 새로운 올림수(carry)와 carry: 올림 수 확인
            boolean finalCarry = sum(tempArr[0], newCarry); // 새로운 올림수와 반쪽 덧셈 결과 중 올림 수 결과를 sum: 최종 올림 수 확인
            boolean[] answer = {finalCarry, tempResult};
            return answer;
        }

        public boolean[] byteadder(boolean[] byteA, boolean[] byteB){
            boolean carry = false;
            boolean[] tempArr;
            int maxLength = Math.max(byteA.length, byteB.length);
            int minLength = Math.min(byteA.length, byteB.length);
            boolean[] answer = new boolean[maxLength+1];

            for(int i=0; i<minLength; i++){ // 공통길이를 갖는 부분 계산
                tempArr = fulladder(byteA[i], byteB[i], carry);
                carry = tempArr[0];
                answer[i] = tempArr[1];
            }

            boolean[] byteTemp = byteA.length > byteB.length? byteA : byteB;
            // 길이가 다를 때 공통 길이를 제외하고 계산
            for(int i=minLength; i<maxLength; i++){
                tempArr = fulladder(byteTemp[i], false, carry);
                carry = tempArr[0];
                answer[i] = tempArr[1];
            }

            answer[maxLength] = carry;

            return answer;
        }
}
