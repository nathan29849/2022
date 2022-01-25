# 싱글 쓰레드와 멀티 쓰레드, 쓰레드의 I/O 블락킹

> 1. main 쓰레드
> 2. 싱글 쓰레드와 멀티 쓰레드
> 3. 쓰레드의 I/O 블락킹(blocking)


## 1. main 쓰레드
- main 메서드의 코드를 수행하는 쓰레드
- 쓰레드는 `사용자 쓰레드`와 `데몬 쓰레드`(보조 역할 쓰레드) 두 종류가 있다.
- 실행 중인 사용자 쓰레드가 하나도 없을 때 프로그램은 종료된다.
   - 데몬 쓰레드가 실행 중이더라도 보조 역할이기 때문에 프로그램은 종료된다.
   - 예를 들어 main 쓰레드와 t1 쓰레드가 있다고 했을 때 main 쓰레드가 종료되더라도 t1 쓰레드가 진행중이라면, 프로그램은 종료되지 않는다.
   

### 예제 코드
```java
public class Ex13_11 {
    static long startTime = 0;

    public static void main(String[] args) {
        ThreadEx11_1 th1 = new ThreadEx11_1();
        ThreadEx11_2 th2 = new ThreadEx11_2();
        th1.start();
        th2.start();
        startTime = System.currentTimeMillis();

//        try {
//            th1.join(); // main 쓰레드가 th1의 작업이 끝날 때까지 기다린다.
//            th2.join(); // main 쓰레드가 th2의 작업이 끝날 때까지 기다린다.
//        } catch (InterruptedException e){
//        }
        System.out.println();
        System.out.println("소요시간 : "+(System.currentTimeMillis() - startTime));
    }
}

class ThreadEx11_1 extends Thread {
    @Override
    public void run() {
        for (int i =0; i< 300; i++){
            System.out.print(new String("-"));
        }
    }
} // run ()


class ThreadEx11_2 extends Thread {
    @Override
    public void run() {
        for (int i =0; i< 300; i++){
            System.out.print(new String("|"));
        }
    }
}
```

### 실행 결과
```
-----|||||
|||||소요시간 : 1
------||||||||||||||||||||||||||||||||||||||||------------
|||||||||||||||||||||||-----------------------------------||||||||||-----||||-
---------------------------|||||||---------|||||||||||||------||||||----------
---||||||||||||||||||----------||||||----|||||||||----------------------------
----|||||||||----|||||||||--||||||||-----------------------------------------
|||||||||||------||||---------|||||||||-------|||||||||||---|||||------------
||------||||||||||||---------------------|||||||-
||||||||||||||||||||||||||||||||||-----------------------
|||||||||||||||||||||||||||||||||

```

- main 쓰레드가 먼저 종료되었더라도, th1, th2가 종료되지 않으면 프로그램이 종료되지 않은 모습을 확인할 수 있다.

- `예제 코드에서 join의 의미` : main 메서드가 다른 작업(쓰레드)가 끝날 때 까지 기다리게 하는 것이다.
   - 주석을 풀고 실행하면 다음과 같이 결과가 나온다.
   
   
```
-------||||||||||||||||||||||||--|||||||||||||||---||||||||||||||||||||----
--------------||||----|||||||||||||||||||||||||||||-----
|||||||||||||||||||||||||||||||-------------------------------||||||-------
----------|||||||||||||||||||||||||||||||||-------------------------------
||||||||||||-----|||||------------------------------------
||||||||||||||||||||||||||||||||||||||-----||||-------------||||||---------
----|||||||||||||||||||-------
|||||||||||||||||||||||||||||||||||||||||||||||||--|||---||----------------
---------------------------------------------------------------------------
-------
   
소요시간 : 8
```

## 2. 싱글 쓰레드와 멀티 쓰레드
### (1) 싱글 쓰레드
![](https://images.velog.io/images/nathan29849/post/ba683e58-0f99-42f2-9072-a8e5c7a1bcf5/image.png)

```java
public class ThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) { // A
            System.out.println("-");
        }

        for (int i = 0; i < 300; i++) { // B
            System.out.println("|");
        }
    }
}
```

- 싱글 쓰레드 작업시 A, B  두 작업이 절대로 겹칠 일이 없다.
 

### (2) 멀티 쓰레드
![](https://images.velog.io/images/nathan29849/post/ffed2f1a-e8e4-49a4-80ad-12cc6867be65/image.png)


```java
public class ThreadTest {
    public static void main(String[] args) {
        MyThread1 th1 = new MyThread1();
        MyThread2 th2 = new MyThread2();
        th1.start();
        th2.start();
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
    }
}

```

- 실제로는 위의 그림처럼 모든 작업이 공통된 시간 길이를 갖는 것이 아니라 OS 스케쥴러에 의해 결정된 시간의 길이를 갖는다. (각기 다를 수 있음)
- 결과적으로 보면 싱글 쓰레드에서 작업한 시간보다 멀티 쓰레드에서 작업한 시간이 약간 더 걸린다.
- A -> B로 넘어가는 것을 Context Switching이라고 하는데, 번갈아가는 시간이 아무래도 조금 더 걸리기 때문에, 한 작업만 계속 진행하는 것 보다는 시간 소요가 생기게 된다.

#### 그럼에도 불구, 멀티 쓰레드를 사용하는 이유??
> - 시간이 조금 더 걸리더라도, 여러 작업을 동시에 할 수 있다는 장점이 있기 때문!
> - 작업을 조금 더 효율적으로 할 수 있게 됨!(아래 쓰레드 I/O 블락킹 참고)

### 3. 쓰레드의 I/O 블락킹(blocking)
- 블락킹(blocking) : 막힘을 의미함 (입출력시 작업이 중단 되는 것을 의미)

```java
import javax.swing.*;

public class ThreadEx7 {
    public static void main(String[] args) {
        ThreadEx7_1 th1 = new ThreadEx7_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 "+input+" 입니다.");
    }
}



class ThreadEx7_1 extends Thread {
    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}

```

- 사용자로부터 입력을 기다리는 구간에 th1이 수행된다.
![](https://images.velog.io/images/nathan29849/post/04630556-201b-425b-95dc-3e2124bb12f7/image.png)

- 싱글 스레드의 경우 입력값이 들어오지 않으면 다른 작업이 진행되지 않고 멈춰있게 됨.