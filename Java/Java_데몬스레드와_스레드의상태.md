# 데몬 쓰레드(daemon thread)
- 일반 쓰레드(non-daemon thread)의 작업을 돕는 **보조적인 역할**을 수행.
- 일반 쓰레드가 모두 종료되면 데몬 쓰레드도 자동적으로 종료된다. (보조역할을 해야할 일반 쓰레드가 없기 때문)
- 가비지 컬렉터(GC), 자동저장, 화면 자동갱신 등에 사용된다.
- 무한루프와 조건문을 이용해서 실행 후 대기하다가 특정조건이 만족되면 작업을 수행하고 다시 대기하도록 작성한다.

> boolean isDaeemon() : 쓰레드가 데몬 쓰레드인지 확인한다. 데몬 쓰레드이면 true를 반환
> void setDaemon(boolean on) : 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경한다. (매개변수 on을 true로 지정하면 데몬 쓰레드가 된다.)

- setDaemon(boolean on)은 반드시 start()를 호출하기 전에 실행되어야 한다.
   - 그렇지 않으면 `IllegalThreadStateException`이 발생한다.

## 예시 코드
```java
public class Ex13_7 implements Runnable{
    static boolean autoSave = false;

    public static void main(String[] args) {
        Thread t = new Thread(new Ex13_7()); // Thread(Runnable r)
        t.setDaemon(true);
        t.start();

        for (int i = 1; i <= 10; i++) {
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){}
            System.out.println(i);
            if (i == 5) {
                autoSave = true;
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    public void run(){
        while (true) {
            try{
                Thread.sleep(3 * 1000);
            } catch(InterruptedException e) {}

            //autoSave의 값이 true이면 autoSave()를 호출
            if(autoSave){
                autoSave();
            }
        }
    }

    public void autoSave(){
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}

```

## 실행 결과

![](https://images.velog.io/images/nathan29849/post/6c465f22-94f2-4d50-ab41-addf69be2bdc/image.png)

---

# 쓰레드의 상태
- 총 다섯 가지의 쓰레드 상태가 존재한다.

|상태|설명|
|--|--|
|NEW|쓰레드가 생성되고 아직 `start()`가 호출되지 않은 상태|
|RUNNABLE|실행 중 또는 실행 가능한 상태|
|BLOCKED|동기화블럭에 의해서 일시정지된 상태(lock이 풀릴 때까지 기다리는 상태)|
|WAITING, </br> TIMED_WAITING|쓰레드의 작업이 종료되지는 않았지만, 실행가능하지 않은(unrunnable) 일시정지 상태. </br> TIMED_WAITING은 일시정지 시간이 지정된 경우를 의미함|
|TERMINATED|쓰레드의 작업이 종료된 상태|

![](https://images.velog.io/images/nathan29849/post/105ea6b7-9f0c-486f-a99f-c4c90818c3cf/image.png)

- 그네타기를 연상해보자.
   - 줄을 서고, 그네를 타다가 쉴 수 있고, 그네 타기를 끝내고 싶으면 TERMINATED가 되면 된다.

