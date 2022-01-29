# 쓰레드의 실행 제어
- 쓰레드의 실행을 제어할 수 있는 메서드가 제공된다.
- 아래의 메서드를 활용해서 보다 효율적인 프로그램의 작성이 가능하다.

![](https://images.velog.io/images/nathan29849/post/6c1d7fb1-3713-4fd3-9d65-73f9f05d4641/image.png)

- static이 붙은 메서드는 다른 쓰레드에는 적용될 수 없고 자기 자신의 쓰레드에게만 호출이 가능하다.

---

## sleep()
- 현재 쓰레드를 지정된 시간동안 멈추게 한다.(static 메서드라서 항상 현재 쓰레드에 대해서만 동작함)

> static void sleep(long millis) // 천분의 일초 단위
> static void sleep(long millis, int nanos) // 천분의 일초 + 나노초

- 잠잘 시간을 인수로 넣어준다. (ex. 3초 = 3 * 1000)
- 예외처리를 해야한다. (`InterruptedException`이 발생하면 깨어남)

```java
try{
   Thread.sleep(1, 5000000); // 쓰레드를 0.0015초 동안 멈추게 한다.
} catch (InterruptedException e) {} // Exception의 자손, 예외처리는 필수적!
```

- 항상 예외처리하는 것이 번거로우므로 아래와 같이 따로 메서드를 만들어 관리하는 방법도 있다.

```java
void delay(long millis) {
   try {
      Thread.sleep(millis);
   } catch(InterruptedException e) {}
}
```

- 특정 쓰레드를 지정해서 멈추게 하는 것은 불가능하다.
   - Thread.sleep(2000)으로 써야함. (클래스 이름으로 호출하는 것이 권장됨. 오해의 소지가 없기 때문)

```java
try{
   th1.sleep(2000); // 다른 thread를 잠자게 할 수 없다!
} catch(InterruptedException e) {}
```

### 예제 코드
```java
public class Ex13_8 {
    public static void main(String[] args) {
        ThreadEx8_1 t1 = new ThreadEx8_1();
        ThreadEx8_2 t2 = new ThreadEx8_2();

        t1.start();
        t2.start();

        delay(2*1000);

        System.out.println("<<main 종료>>");

    }

    static void delay(long millis){
        try{
//            t1.sleep(2000);  // t1을 2초동안 잠자게 ?? -> main 스레드가 잠듦
            Thread.sleep(millis);
        } catch (InterruptedException e){}
    }
}

class ThreadEx8_1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.printf("\n<<t1 종료>>\n");
    }
}


class ThreadEx8_2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.printf("\n<<t2 종료>>\n");
    }
}
```


### 실행 결과

![](https://images.velog.io/images/nathan29849/post/119cb0ed-45de-4870-97e4-8e5ae5c60d1d/image.png)

- sleep된 main 스레드가 가장 늦게 종료됨을 확인할 수 있다.
- 만약 `delay()`를 주석처리한다면, main 스레드는 가장 먼저 종료된다. (t1, t2 start 이후 하는게 없기 때문)

---

## interrupt()
- 대기상태(WAITING)인 쓰레드를 실행대기 상태(RUNABLE)로 만든다.
   - 대기상태(WAITING) : 작업이 중단된 상태를 의미 (`sleep()`, `join()`, `wait()` 등의 메서드들에 의해 쓰레드가 중단됨)
   
> void interrupt() : 쓰레드의 interrupted 상태를 false에서 true로 변경
> boolean isInterrupted() : 쓰레드의 interrupted 상태를 반환.(내가 잠을 다 자고 일어난건지, 중간에 누가 깨워서 일어난건지 파악이 가능)
> static boolean interrupted() : 현재 쓰레드의 interrupted 상태를 알려주고, false로 interrupted 상태를 초기화

![](https://images.velog.io/images/nathan29849/post/237ab0af-9f0e-4f79-b783-6be35ba247a6/image.png)


### 예제 코드

```java
import javax.swing.*;

public class Ex13_9 {
    public static void main(String[] args) {
        ThreadEx9_1 th1 = new ThreadEx9_1();
        th1.start();
        System.out.println("isInterrupted(): " + th1.isInterrupted()); // false
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
        System.out.println("읿력하신 값은 "+input+"입니다.");
        th1.interrupt();
//        System.out.println("isInterrupted(): " + th1.isInterrupted()); // true
//        System.out.println("isInterrupted(): " + th1.isInterrupted()); // true
//        System.out.println("interrupted(): " + Thread.interrupted()); // false - 현재 쓰레드는 main 쓰레드임.
    }
}

class ThreadEx9_1 extends Thread{
    @Override
    public void run() {
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            for(long x=0; x<25000000000L; x++); // 시간 지연
        }
        System.out.println("isInterrupted(): " + this.isInterrupted()); // true
        System.out.println("  "+Thread.interrupted()); // true
        System.out.println("  "+Thread.interrupted()); // false
        System.out.println("카운트가 종료되었습니다.");
    }
}

```

### 실행 결과

![](https://images.velog.io/images/nathan29849/post/5166c492-6096-48e6-aca8-236cceda2201/image.png)

- `isInterrupted()`와는 달리 `interrupted()`는 `interrupted`라는 상태변수를 false로 초기화한다.

---

## suspend(), resume(), stop()
- 쓰레드의 실행을 일시정지, 재개, 완전 정지 시킨다.
- 사용이 권장되지 않음.(deprecated)
   - dead-lock, 교착상태에 빠질 위험이 있기 때문
   
- 따라서 아래와 같이 작성해서 사용하면 된다.
```java
public class ThreadEx17_1 implements Runnable{
    boolean suspended = false;
    boolean stopped = false;
    
    @Override
    public void run(){
        while(!stopped){
            if(!suspended){
                // 쓰레드가 수행할 코드를 작성
            }
        }
    }
    
    public void suspend(){suspended = true;}
    public void resume(){suspended = false;}
    public void stop(){stopped = true;}
}

```

### 예제 코드

```java
public class Ex13_10 {
    public static void main(String[] args) {
        MyThread th1 = new MyThread("*");
        MyThread th2 = new MyThread("**");
        MyThread th3 = new MyThread("***");

        th1.start();
        th2.start();
        th3.start();

        try{
            Thread.sleep(2000);
            th1.suspended();
            Thread.sleep(2000);
            th2.suspended();
            Thread.sleep(3000);
            th1.resume();
            Thread.sleep(3000);
            th1.stop();
            th2.stop();
            Thread.sleep(2000);
            th3.stop();
        } catch (InterruptedException e){}
    }
}

class MyThread implements Runnable{
    volatile boolean suspended = false;  // volatile : 쉽게 바뀌는 변수라는 의미
    volatile boolean stopped = false; // volatile 을 붙이는 이유 : CPU 코어가 여러개 존재하는데, 그 코어 안에 cache가 존재함. 복사본을 사용하지 못하게 함으로써 값을 보존

    Thread th;
    MyThread(String name){
        th = new Thread(this, name); // Thread(Runnable r, String name)
    }

    void start(){
        th.start();
    }

    void stop(){
        stopped = true;
    }

    void suspended(){
        suspended = true;
    }

    void resume(){
        suspended = false;
    }

    @Override
    public void run() {
        while (!stopped){
            if(!suspended){
                System.out.println(Thread.currentThread().getName());
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){}
            }
        }
    }
}

```
- volatile : 쉽게 바뀌는 변수라는 의미
- volatile 을 붙이는 이유 : CPU 코어가 여러개 존재하는데, 그 코어 안에 cache가 존재함. 이 때 각 cache는 데이터를 효율적으로 관리하기 위해 복사본을 사용하여 관리하는데, 이로인해 원본 값과 매칭이 되지 않는 현상이 발생할 수 있음. 따라서 volatile을 붙임으로써 복사본을 사용하지 못하게 함으로써 값을 보존함.

### 실행 결과

![](https://images.velog.io/images/nathan29849/post/b8c65eb8-ce3b-4ce4-9c0d-9db735297f89/image.png)

---

## join()
- 지정된 시간동안 특정 쓰레드가 작업하는 것을 기다린다.

> void join() : 작업이 모두 끝날 때 까지
> void join(long millis) : 천분의 일초 동안
> void join(long millis, int nanos) : 천분의 일초 + 나노초 동안

- 예외처리를 해야한다. (`InterruptedException`이 발생하면 작업 재개)
```java
public class Ex13_11 {
    static long startTime = 0;

    public static void main(String[] args) {
        ThreadEx11_1 th1 = new ThreadEx11_1();
        ThreadEx11_2 th2 = new ThreadEx11_2();
        th1.start();
        th2.start();
        startTime = System.currentTimeMillis();

        try {
            th1.join(); // main 쓰레드가 th1의 작업이 끝날 때까지 기다린다.
            th2.join(); // main 쓰레드가 th2의 작업이 끝날 때까지 기다린다.
        } catch (InterruptedException e){
        }
        System.out.println();
        System.out.println("소요시간 : "+(System.currentTimeMillis() - startTime));
    }
}
```

### 예시 코드(가비지 컬렉터 - 데몬 쓰레드)
```java
public void run() {
   while(true){
      try{
         Thread.sleep(10*1000); // 10초를 기다린다
      } catch(InterruptedException e){
         System.out.println("Awaken by interrupt().");
      }
      
      gc(); // garbage collection을 수행한다.
      System.out.println("Garbate Collected. Free Memory : "+ freeMemory());
   }
}
```


```java
for(int i=0; i < 20; i++){
   requiredMemory = (int)(Math.random() *10) *20;
   // 필요한 메모리가 사용할 수 있는 양보다 더 적거나, 전체 메모리의 60% 이상 사용했을 경우 gc를 깨운다.
   if(gc.freeMemory() < requireMemory ||
      gc.freeMemorty() < gc.totalMemory() * 0.4)
   {
      gc.interrupt(); // 잠자고 있는 쓰레드 gc를 깨운다.
      
      try { // 깨우기만 하면, 바로 메모리에 못 쓰기 때문에 꼭 join을 해줘야 한다.(gc() 수행시간 확보)
         gc.join(100); // gc가 메모리 정리할 시간 확보
      } catch (InterruptedException e) {}
   }
   
   gc.usedMemory += requiredMemory;
   System.out.println("usedMemory:"+gc.usedMemory);
}
```

---

## yield() - static method
- 남은 시간을 다음 쓰레드에게 양보하고, 자신(현재 쓰레드)은 실행대기 한다.
- yield()와 interrupt()를 적절히 사용하면, 응답성과 효율을 높일 수 있다.
    - 만약 yield() 없이 interrupt()만 사용했다면, while(!stopped)를 계속해서 수행하기 때문에 `busy-waiting` 상태가 된다.
    - 따라서 코드를 개선하면 다음과 같다. (yield() 사용)
    
    
```java
public class MyThreadEx18 implements Runnable{
    boolean suspended = false;
    boolean stopped = false;

    Thread th;

    MyThreadEx18(String name){
        th = new Thread(this, name);
    }

    public void run() {
        while (!stopped) {
            if (!suspended) {
                // 작업 수행
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            } else {
                Thread.yield();
            }
        }
    }

    public void start(){th.start();}
    public void resume(){suspended=false;}

    public void suspend() {
        suspended = true;
        th.interrupt(); // 깨워야 바로 반영됨 (응답성이 좋아짐)
    }

    public void stop(){
        stopped = true;
        th.interrupt(); // 깨워야 바로 반영됨 (응답성이 좋아짐)
    }

}
```

- 그러나.. `yield()`도 반드시 동작한다는 보장은 없다.
   - OS 스케쥴러에 통보하는 것 뿐이다.. (큰 차이가 없다고 함..)
