package cs02;

public class VideoDataStructure {
    private String id; // 고유한 id 값
    private String title = "제목"; // 제목 문구
    private static int title_id = 1; // 제목 뒤에 붙는 숫자 (1부터 시작)
    private byte playTime; // 영상 재생 시간(초단위) (1초 ~ 15초 사이의 값만 들어가므로 byte 자료형을 사용)
    private VideoDataStructure next = null;// 다음 영상 정보에 대한 연결(초기값 null)
    private StringBuffer sb;

    VideoDataStructure(String id, byte playTime) {
        this.id = id;
        this.title += title_id; // ex. 제목1, 제목2, 제목3, ...
        this.playTime = playTime; // 재생 시간은 랜덤하게 1초에서 15초 내로 지정한다. (이부분 다시 고려해야할 듯 - 생성자 만들때 제한할 수 있는 방법이 있는가..)
        // 다음 영상 정보는 우선 지정하지 않는다.
        title_id++;

    }

    void link(VideoDataStructure vd) {
        this.next = vd;
    }

    void printInfo() {
        sb = new StringBuffer();
        sb.append(this.title);
        sb.append(" (");
        sb.append(this.id);
        sb.append(") : ");
        sb.append(this.playTime);
        System.out.println(sb); // ex. 제목1(abcd):12
    }

    String getId(){
        return this.id;
    }

    byte getPlayTime(){
        return this.playTime;
    }

    VideoDataStructure getNext(){
        return this.next;
    }
}