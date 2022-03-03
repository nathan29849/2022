package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance; // 이걸로만 조회가 가능
    }

    private SingletonService() {
        // 외부에서 new 연산자로 생성하지 못하도록 private 지정
        // 이로 인해 getInstance()로 딱 하나의 객체만 사용할 수 있게 된다.
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}

