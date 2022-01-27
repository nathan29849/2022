# 쓰레드의 우선순위, 쓰레드 그룹

> 1. 쓰레드의 우선순위
> 2. 쓰레드 그룹

---

## 1. 쓰레드의 우선순위 (priority of thread)
- 작업의 중요도에 따라 쓰레드의 우선순위를 다르게 하여 특정 쓰레드가 더 많은 작업시간을 갖게 할 수 있다.
- JVM에서는 쓰레드의 우선순위를 1부터 10까지 보유할 수 있게 되어 있음.

> void setPriority(int newPriority) // 쓰레드의 우선순위를 지정한 값으로 변경한다.
> int getPriority() // 쓰레드의 우선순위를 반환한다.
> public static final int MAX_PRIORITY = 10; // 최대 우선 순위
> public static final int MIN_PRIORITY = 1; // 최소 우선 순위
> public static final int NORM_PRIORITY = 5; // 보통 우선 순위

- 쓰레드의 우선순위를 따로 지정해주지 않으면 5로 자동 지정이 된다.
- 우선순위는 쓰레드가 시작된 이후에도 변경이 가능하다.
- 우리가 여기서 지정하는 우선순위는 단지 **희망사항**에 불과하다. (결국은 모든 것을 바탕으로 OS의 스케쥴러가 결정함)

![](https://images.velog.io/images/nathan29849/post/fea56653-e712-49ca-a0f8-68ad54a67183/image.png)

### 예제 코드

```java
public class Ex13_6 {
    public static void main(String[] args) {
        ThreadEx6_1 th1 = new ThreadEx6_1();
        ThreadEx6_2 th2 = new ThreadEx6_2();

        th2.setPriority(10);

        System.out.println("Priority of th1(-) : "+th1.getPriority());
        System.out.println("Priority of th2(|) : "+th2.getPriority());

        th1.start();
        th2.start();
    }
}

class ThreadEx6_1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
            for(int x =0; x <1000000; x++); // 시간지연용 for문
        }
    }
}

class ThreadEx6_2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
            for(int x =0; x <1000000; x++);
        }
    }
}
```

### 실행 결과
```
Priority of th1(-) : 5
Priority of th2(|) : 10
-|-|------------------------------|||||||||---|||||||----------------
||||||||||||||||||||------------||||||||||------|||||||||||||||||||||---------
-||||||||||||||||||||-----|||||||||||||||||||||||||||||||||||||||-------------
----||||||||||||||------------------------------------------------------------
----------|||||||||||||||||||||||||||||||||||||||||||||||||-------------------
-------|||||||------------------------------|||------|||||---|||-------|||----
------------||||||||||------------------------|||||||||||--------||||||||||---
------|||||||||||||||||||||||||||||||||||||||||||||||||||||||||
```

- 우선순위가 높다고해서 항상 일찍 끝나게 되는 것은 아님 (확률상.. 높아진다는 의미)

### 여기서 잠깐
> 마우스 포인터의 우선순위가 엄청 높다고 한다.
> 그 이유는 다른 프로그램 때문에 마우스 포인터가 안움직여버리면 사용자가 많이 답답할 수 있기 때문이라고 한다..ㅋ ㅋ

---

## 2. 쓰레드 그룹
- 서로 관련된 쓰레드를 그룹으로 묶어서 다루기 위한 것
- 모든 쓰레드는 반드시 하나의 쓰레드 그룹에 포함되어 있어야 한다.
- 쓰레드 그룹을 지정하지 않고 생성한 쓰레드는 `main 쓰레드 그룹`에 속한다.

- 아래와 같이 쓰레드 생성자를 통해 그룹을 지정할 수 있다.
> Thread(ThreadGroup group, String name)
> Thread(ThreadGroup group, Runnable target)
> Thread(ThreadGroup group, Runnable target, String name)
> Thread(ThreadGroup group, Runnable target, String name, long stackSize)


- 관련된 메서드를 통해 쓰레드 그룹 관련 작업을 처리할 수 있다.
> ThreadGroup getThreadGroup() // 쓰레드 자신이 속한 쓰레드 그룹을 반환한다. (Thread에 있는 메서드)
> void uncaughtException(Thread t, Throwable e) // 처리되지 않은 예외에 의해 쓰레드 그룹의 쓰레드가 실행이 종료되었을 때, JVM에 의해 이 메서드가 자동적으로 호출된다. (ThreadGroup에 있는 메서드)

### 쓰레드 그룹의 메서드
![](https://images.velog.io/images/nathan29849/post/243b8a3b-8cd1-4b1b-a91a-ea3e999f1dc3/image.png)


