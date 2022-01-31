# Executors
- High-Level Concurrency 프로그래밍을 하기위해서는?
   - 스레드를 만들고, 관리하는 작업을 애플리케이션에서 분리해야 한다.
   - Java 8부터는 그런 기능을 Executors에게 위임할 수 있게 됐다.

## Executors가 하는 일
- 스레드 만들기 : 애플리케이션이 사용할 스레드 풀(Thread pool)을 만들어 관리한다.
- 스레드 관리 : 스레드 생명 주기를 관리한다.
- 작업 처리 및 실행 : 스레드로 실행할 작업을 제공할 수 있는 API를 제공한다.

## 주요 인터페이스
- `Executor` : execute(Runnable)
- `ExecutorService` : Executor를 상속받은 인터페이스로, Runnable 뿐만 아니라, Callable로도 실행할 수 있으며, Executor를 종료시키거나, 여러 Callable을 동시에 실행하는 등의 기능을 제공한다. (병렬 작업 시 여러 개의 작업을 효율적으로 처리할 수 있다.)
- `ScheduledExecutorService` : ExecutorService를 상속받은 인터페이스로 특정 시간 이후에 또는 주기적으로 작업을 실행할 수 있다.

### ExecutorService
![](https://images.velog.io/images/nathan29849/post/58ee06d7-96d7-4490-bb65-23892cf4e5cc/image.png)
- 각기 다른 스레드를 생성해서 작업을 처리하고, 완료되면, 해당 스레드를 제거하는 일련의 작업을 직접 진행하지 않고 `ExecutorService`를 통해 쉽게 처리가 가능하다.
- `ExecutorService`에 Task만 지정해주면 친절하게 알아서 스레드 풀을 이용하여 Task를 실행하고 관리한다.
- Task는 BlockingQueue로 관리되며, 스레드 풀에 존재하는 스레드들이 모두 작업중일 때, 남은 작업 요청을 이 BlockingQueue에 쌓아 놓는다. (수행을 마친 스레드로 할당되어 순차적으로 실행)
   - 스레드를 작업요청만큼 생산하지 않으므로 스레드 생성 비용이 덜 든다는 장점이 있다.

### ExecutorService의 생성과 종료
```java
ExecutorService executorService = Executors.newSingleThreadExecutor();

executorService.execute(() -> System.out.println("Thread "+Thread.currentThread().getName()));
//  executorService.submit(() -> System.out.println("Thread " + Thread.currentThread().getName())); // 위와 의미 같음
// 이렇게 하면 실행이 되는데, 주의해야할 점은, 작업을 실행하고 나면, 다음 작업이 들어올 때 까지 계속해 대기상태에 있게 됨.
// -> 프로세스가 죽지않고 계속 켜져있게 됨... 따라서 셧다운을 해줘야 함
executorService.shutdown(); // graceful shutdown : 현재 진행중인 작업은 끝까지 마치고 끝내는 것
// executorService.shutdownNow(); // graceful shutdown과 달리 바로 죽임

>>>
Thread pool-1-thread-1
```

### 스레드가 여러 개 있는 스레드 풀 만들기
```java
// Executors 내부에 스레드 풀이 존재함. 따라서 다음처럼 스레드 3개 있는 풀 생성도 가능
ExecutorService es = Executors.newFixedThreadPool(3);
es.submit(getRunnable("Hello"));
es.submit(getRunnable("World"));
es.submit(getRunnable("The"));
es.submit(getRunnable("Java"));
es.submit(getRunnable("Thread"));

>>>
Hellopool-2-thread-1
Worldpool-2-thread-2
Javapool-2-thread-2
Thepool-2-thread-3
Threadpool-2-thread-1
JAVA~pool-3-thread-1
```

### ScheduledExecutorService로 시간 지정해서 작업 수행하기
```java
ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
ses.schedule(getRunnable("Hello!!"), 3, TimeUnit.SECONDS); // 지정한 시간 이후에 실행될 수 있도록 작업 요청이 가능하다.
ses.scheduleAtFixedRate(getRunnable("JAVA~"), 1, 2, TimeUnit.SECONDS); // 반복 Rate를 지정해서 작업 요청을 할 수 있다.

>>>
JAVA~pool-3-thread-1
Hello!!pool-3-thread-1
JAVA~pool-3-thread-1
JAVA~pool-3-thread-1
JAVA~pool-3-thread-1
...
```

### Example Code
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Executor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> System.out.println("Thread "+Thread.currentThread().getName()));
//        executorService.submit(() -> System.out.println("Thread " + Thread.currentThread().getName())); // 위와 의미 같음
        // 이렇게 하면 실행이 되는데, 주의해야할 점은, 작업을 실행하고 나면, 다음 작업이 들어올 때 까지 계속해 대기상태에 있게 됨.
        // -> 프로세스가 죽지않고 계속 켜져있게 됨... 따라서 셧다운을 해줘야 함
        executorService.shutdown(); // graceful shutdown : 현재 진행중인 작업은 끝까지 마치고 끝내는 것
        // executorService.shutdownNow(); // graceful shutdown과 달리 바로 죽임

        // Executors 내부에 스레드 풀이 존재함. 따라서 다음처럼 스레드 두 개 있는 풀 생성도 가능
        ExecutorService es = Executors.newFixedThreadPool(3);
        es.submit(getRunnable("Hello"));
        es.submit(getRunnable("World"));
        es.submit(getRunnable("The"));
        es.submit(getRunnable("Java"));
        es.submit(getRunnable("Thread"));


        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.schedule(getRunnable("Hello!!"), 3, TimeUnit.SECONDS); // 지정한 시간 이후에 실행될 수 있도록 작업 요청이 가능하다.
        ses.scheduleAtFixedRate(getRunnable("JAVA~"), 1, 2, TimeUnit.SECONDS); // 반복 Rate를 지정해서 작업 요청을 할 수 있다.


        // 지금까지는 Runnable만 사용 return type void
        // 별도의 스레드에서 실행한 작업에 대한 결과물(return 값)을 가져오고 싶을때는 Runnable로 불가능하다.
        // Callable로 가져올 수 있다.(Runnable과 같은데, return할 수 있다는 점에서 차이가 있음)
        // 그 return 값을 받아올 것이 바로 "Future"이다.

    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}

>>>
Thread pool-1-thread-1
Hellopool-2-thread-1
Worldpool-2-thread-2
Javapool-2-thread-2
Thepool-2-thread-3
Threadpool-2-thread-1
JAVA~pool-3-thread-1
Hello!!pool-3-thread-1
JAVA~pool-3-thread-1
JAVA~pool-3-thread-1
JAVA~pool-3-thread-1
...

```

## Next..?
- 지금까지는 `Runnable`만 사용 (return type void)
- 별도의 스레드에서 실행한 작업에 대한 결과물(return 값)을 가져오고 싶을때는 `Runnable`로 불가능하다.
- `Callable`로 가져올 수 있다.(`Runnable`과 같은데, return할 수 있다는 점에서 차이가 있음)
- 그 return 값을 받아올 것이 바로 `Future`이다.
- 따라서 다음 포스트에서는 `Callable`과 `Future`에 대해서 알아보도록 하자.


---
## Reference
- [[Java] ExecutorService란?](https://simyeju.tistory.com/m/119)
- [https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html]()