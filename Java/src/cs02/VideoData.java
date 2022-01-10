package cs02;

import java.util.ArrayList;

public class VideoData {
    private static ArrayList<VideoDataStructure> bascket;

    public static void main() {
        bascket = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            byte randomNumber = (byte) (Math.random() * 15 + 1); // 0<= N < 16
            String id;
            while(true){
                id = createId(); // id 4자리 소문자 알파벳 조합(랜덤생성)
                if (!(checkId(id))){
                    break;
                }
            }
            VideoDataStructure video = new VideoDataStructure(id, randomNumber); // videoData 객체 생성
            bascket.add(video); // 생성 객체를 콜렉션에 보관
        }
        System.out.println("---영상클립---");
        for (int j = 0; j < bascket.size(); j++) {
            VideoDataStructure videoInfo = bascket.get(j);
            videoInfo.printInfo();
        }
    }

    private static String createId() {
        String id = "";
        for (int j = 0; j < 4; j++) {
            id += (char) (Math.random() * 26 + 97); // ascii code
        }
        return id;
    }

    public static boolean checkId(String id){
        for (int i=0; i< bascket.size(); i++){
            String nowId = bascket.get(i).id;
            if(nowId.equals(id)){
                return true;
            }
        }
        return false;
    }

    public static VideoDataStructure searchId(String id){
        VideoDataStructure now;
        for (int i=0; i< bascket.size(); i++){
            now = bascket.get(i);
            if(now.id.equals(id)){
                return now;
            }
        }
        return null;
    }
}
