package cs02;

import static cs02.VideoData.*;

public class Editor {
    public static VideoDataStructure FirstVideo = null; // head
    public static VideoDataStructure LastVideo = null; // tail

    public static void add(String id) {
        if (!(checkId(id))){
            System.out.println("해당 id가 영상 목록에 없습니다. ");
            return;
        }

        if (existCheck(id)){
            System.out.println("해당 id는 이미 존재하는 영상 클립입니다.");
            return;
        }

        VideoDataStructure video = searchId(id);
        if (FirstVideo == null) {    // 데이터가 아예 없는 경우
            FirstVideo = video;
            LastVideo = video;
        } else {
            LastVideo.link(video);
            LastVideo = video;
        }
        printVideos();
    }

    public static void insert(String id, int location) {
        if (!(checkId(id))){ // insert하고자 하는 id가 "영상 목록" 없는 경우
            System.out.println("해당 id가 영상 목록에 없습니다. ");
            return;
        }

        if (existCheck(id)){
            System.out.println("해당 id는 이미 존재하는 영상 클립입니다.");
            return;
        }

        VideoDataStructure video = searchId(id);
        VideoDataStructure priorVideo = null;
        VideoDataStructure nowVideo = FirstVideo;
        int i = 0;
        while (nowVideo != null) { // location이 linkedlist 길이를 초과하는 경우도 대비
            if (i == location){
                break;
            }
            priorVideo = nowVideo; // 현재 비디오 -> 이전 비디오
            nowVideo = nowVideo.next; // 다음 비디오 -> 현재 비디오
            i++;
        }
        if (priorVideo != null){
            priorVideo.link(video);
            video.link(nowVideo);
        } else {
            video.link(FirstVideo);
            FirstVideo = video;
        }
        printVideos();
    }

    public static void delete(String id) {
        int cnt = countVideos();
        if (cnt==0){
            System.out.println("해당 id가 영상 목록에 없습니다.");
            return;
        }
        VideoDataStructure priorVideo = null;
        VideoDataStructure nowVideo = FirstVideo;
        boolean flag = false;
        while (nowVideo != null) {
            if (nowVideo.id.equals(id)) {
                flag = true;
                break;
            }
            priorVideo = nowVideo;
            nowVideo = nowVideo.next;
        }

        if (flag){
            if (cnt == 1){
                FirstVideo = null;
            } else {
                priorVideo.link(nowVideo.next); // 이전 데이터의 next에 다음 데이터를 넣기
                nowVideo.link(null);
            }
        } else {
            System.out.println("일치하는 id가 없습니다.");
            return;
        }
        printVideos();
    }

    public static void render() {
        int numberOfVideos = 0; // 영상 클립의 개수
        int videoLength = 0; // 전체 길이

        VideoDataStructure now = FirstVideo;
        while (now != null) {
            numberOfVideos++;
            videoLength += now.playTime;
            now = now.next;
        }
        System.out.println("영상클립: " + numberOfVideos + "개");
        System.out.println("전체길이: " + videoLength + "sec");
    }

    private static void printVideos() {
        VideoDataStructure now = FirstVideo;
        System.out.print("|---");
        while (now != null) {
            System.out.print("[" + now.id + ", " + now.playTime + "sec]---");
            now = now.next;
        }
        System.out.println("[end]");
    }

    private static int countVideos(){
        int cnt = 0;
        VideoDataStructure now = FirstVideo;
        while(now != null){
            now = now.next;
            cnt++;
        }
        return cnt;
    }

    private static boolean existCheck(String id){
        VideoDataStructure now = FirstVideo;
        while (now != null){
            if (now.id.equals(id)){
                return true;
            }
            now = now.next;
        }
        return false;
    }
}
